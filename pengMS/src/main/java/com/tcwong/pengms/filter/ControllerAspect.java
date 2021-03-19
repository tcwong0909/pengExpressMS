package com.tcwong.pengms.filter;

import com.alibaba.fastjson.JSONObject;
import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.constant.ExceptionTypeEnum;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.constant.SessionConstant;
import com.tcwong.pengms.dto.LogDTO;
import com.tcwong.pengms.listen.LogEvent;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.utils.ApplicationPublishUtil;
import com.tcwong.pengms.utils.IpUtil;
import com.tcwong.pengms.utils.SessionUtil;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description 系统日志全局处理
 *
 * @author tcwong
 * @date 2020/8/24 Since 1.8
 */
@Aspect
@Component
public class ControllerAspect {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Autowired private HttpServletRequest httpServletRequest;

    /**
     * Description 定义切点
     *
     * @author tcwong
     * @date 2020/8/24 Since 1.8
     */
    @Pointcut("execution(* com.tcwong.pengms.controller.*.*(..))")
    public void pointcut() {}

    /**
     * Description 请求前
     *
     * @param joinPoint 切点
     * @return
     * @author tcwong
     * @date 2020/8/24
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        Signature signature = joinPoint.getSignature();
        // 类名
        String targetClassName = signature.getDeclaringType().getSimpleName();
        // 方法名
        String methodName = signature.getName();
        // 参数列表
        Object[] args = joinPoint.getArgs();
        String paramJSON = JSONObject.toJSONString(args);
    }

    /**
     * Description 值返回后
     *
     * @author tcwong
     * @date 2020/8/24 Since 1.8
     */
    @AfterReturning(pointcut = "pointcut()", returning = "object")
    public void afterReturning(JoinPoint joinPoint, Object object) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        String paramJSON = JSONObject.toJSONString(args);
        // 获取类字节码
        Class<?> targetClazz = joinPoint.getTarget().getClass();
        // 获取类下所有方法
        Method[] methods = targetClazz.getDeclaredMethods();
        // 获取切点目标方法
        Method targetMethod =
                Arrays.stream(methods)
                        .filter(method -> method.getName().equals(methodName))
                        .filter(method -> method.getParameterCount() == args.length)
                        .findFirst()
                        .get();
        LogFilter logFilter = targetMethod.getAnnotation(LogFilter.class);
        if (logFilter != null) {
            User user = SessionUtil.getAttribute(SessionConstant.SESSION_USER, User.class);
            LogDTO logDTO =
                    new LogDTO(
                            logFilter.logOperationType().getDescription(),
                            logFilter.description(),
                            paramJSON,
                            IpUtil.getIpAddr(httpServletRequest),
                            ExceptionTypeEnum.NORMAL.getType(),
                            "",
                            user.getUserAccount(),
                            user.getUsername());
            ApplicationPublishUtil.publish(new LogEvent(logDTO));
        }
    }

    /**
     * Description 切面异常时处理
     *
     * @param joinPoint 切入点
     * @param throwable 异常
     * @return
     * @author tcwong
     * @date 2020/9/1
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void AfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数
        Object[] args = joinPoint.getArgs();
        String paramJSON = JSONObject.toJSONString(args);
        // 获取类字节码
        Class<?> targetClazz = joinPoint.getTarget().getClass();
        // 获取类下所有方法
        Method[] methods = targetClazz.getDeclaredMethods();
        // 获取切点目标方法
        Method targetMethod =
                Arrays.stream(methods)
                        .filter(method -> method.getName().equals(methodName))
                        .filter(method -> method.getParameterCount() == args.length)
                        .findFirst()
                        .get();
        LogFilter logFilter = targetMethod.getAnnotation(LogFilter.class);
        LogDTO logDTO;
        User user = SessionUtil.getAttribute(SessionConstant.SESSION_USER, User.class);
        if (logFilter != null) {
            logDTO =
                    new LogDTO(
                            logFilter.logOperationType().getDescription(),
                            logFilter.description(),
                            paramJSON,
                            IpUtil.getIpAddr(httpServletRequest),
                            ExceptionTypeEnum.NORMAL.getType(),
                            "",
                            user.getUserAccount(),
                            user.getUsername());
        } else {
            logDTO =
                    new LogDTO(
                            LogOperationType.OTHER.getDescription(),
                            methodName,
                            paramJSON,
                            IpUtil.getIpAddr(httpServletRequest),
                            ExceptionTypeEnum.EXCEPTION.getType(),
                            throwable.getMessage(),
                            user.getUserAccount(),
                            user.getUsername());
        }
        ApplicationPublishUtil.publish(new LogEvent(logDTO));
    }
}
