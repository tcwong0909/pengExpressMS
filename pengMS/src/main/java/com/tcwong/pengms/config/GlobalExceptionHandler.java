package com.tcwong.pengms.config;

import com.tcwong.pengms.base.BaseException;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.utils.LocaleMessageUtil;
import com.tcwong.pengms.utils.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description 全局异常处理
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

    /**
     * Description 自定义异常同意处理
     *
     * @param baseException 自定义异常
     * @return
     * @author tcwong
     * @date 2020/9/1
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public WebResponse baseExceptionHandler(BaseException baseException) {
        String exceptionCode = baseException.getExceptionCode();
        LogUtil.error("异常信息:{}", LocaleMessageUtil.getMessage(exceptionCode));
        return WebResponse.failed(exceptionCode,baseException.getArgs());
    }

    /**
     * Description 系统异常返回
     *
     * @param e 系统异常
     * @return
     * @author tcwong
     * @date 2020/9/1
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebResponse exceptionHandler(Exception e){
        LogUtil.error("异常信息:{}",e.getMessage());
        return WebResponse.failed("9999");
    }
}
