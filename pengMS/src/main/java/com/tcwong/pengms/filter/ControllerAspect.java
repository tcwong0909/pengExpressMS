package com.tcwong.pengms.filter;

import com.alibaba.fastjson.JSONObject;
import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.dto.LogDTO;
import com.tcwong.pengms.listen.LogEvent;
import com.tcwong.pengms.service.SyslogService;
import com.tcwong.pengms.utils.ApplicationPublishUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Description 系统日志全局处理
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
@Aspect
@Component
public class ControllerAspect {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * Description 定义切点
     *
     * @author tcwong
     * @date 2020/8/24
     * Since 1.8
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
        //类名
        String targetClassName = signature.getDeclaringType().getSimpleName();
        //方法名
        String methodName = signature.getName();
        //参数列表
        Object[] args = joinPoint.getArgs();
        String paramJSON = JSONObject.toJSONString(args);
    }

    /**
     * Description 值返回后
     *
     * @author tcwong
     * @date 2020/8/24
     * Since 1.8
     */
    @AfterReturning(pointcut = "pointcut()",returning = "object")
    public void afterReturning(JoinPoint joinPoint,Object object) throws ClassNotFoundException {
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //获取类字节码
        Class<?> targetClazz = joinPoint.getTarget().getClass();
        //类名
        String targetClassName = targetClazz.getSimpleName();
        //获取类下所有方法
        Method[] methods = targetClazz.getDeclaredMethods();
        //获取切点目标方法
        Method targetMethod = Arrays.stream(methods).filter(method -> method.getName().equals(methodName))
                .filter(method -> method.getParameterCount() == args.length).findFirst().get();
        LogFilter logFilter = targetMethod.getAnnotation(LogFilter.class);
        if (logFilter != null) {
            ApplicationPublishUtil.publish(new LogEvent(new LogDTO(null)));
        }

    }

}
