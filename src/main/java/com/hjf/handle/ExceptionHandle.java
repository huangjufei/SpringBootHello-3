package com.hjf.handle;

import com.hjf.exception.GirlException;
import com.hjf.utils.Result;
import com.hjf.utils.ResultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述:全局异常处理
 * 思路:不管是service,controller层都向外抛出异常,然后在这里来捕获后统一处理
 * 好处:1,代码减少判断减少;
 * 2,让代码可读取性变高;流程清晰且;且返回出前端的异常很清晰;
 * 3,日志可以在这里统一处理
 * 
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    //统一的异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        logger.info("统一异常--------------------");
        if(e instanceof GirlException){
            GirlException girlException= (GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }else {
            logger.error("系统异常{}",e);
            return ResultUtil.error(-1, "未知错误");
        }
    }

}
