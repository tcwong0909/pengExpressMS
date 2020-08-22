package com.tcwong.pengms.config;

import com.tcwong.pengms.base.BaseException;
import com.tcwong.pengms.utils.LogUtil;
import com.tcwong.pengms.utils.WebResponse;
import com.tcwong.pengms.utils.YTException;
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

    @ExceptionHandler(YTException.class)
    @ResponseBody
    public WebResponse jsonHandler(YTException e){
        e.printStackTrace();
        LogUtil.error(e);
        return new WebResponse(e.getErrorCode()+"",e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public WebResponse baseExceptionHandler(BaseException baseException) {
        LogUtil.error("异常信息:{}",baseException.getExceptionMessage());
        return new WebResponse(baseException.getExceptionCode(), baseException.getExceptionMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse exceptionHandler(Exception e){
        e.printStackTrace();
        LogUtil.error(e);
        return new WebResponse("-1",e.getMessage());
    }
}
