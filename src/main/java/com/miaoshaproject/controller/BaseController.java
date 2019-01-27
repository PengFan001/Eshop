package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    //定义一个exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletResponse response, Exception e){

        Map<String, Object> responseData = new HashMap<>();

        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException)e;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
        }
        else {
            responseData.put("errCode", EmBusinessError.UNKNOWED_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWED_ERROR.getErrMsg());
        }

        return CommonReturnType.create(responseData, "fail");
    }

}
