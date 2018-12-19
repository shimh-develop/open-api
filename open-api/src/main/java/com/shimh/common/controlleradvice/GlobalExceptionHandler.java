package com.shimh.common.controlleradvice;

import com.shimh.common.base.Result;
import com.shimh.common.constant.ResultCode;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result AuthorizationExceptionHandler(HttpServletRequest request, AuthorizationException e) {

        logger.error("权限认证错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        return Result.error(ResultCode.NO_ACCESS_PERMISSION);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result UnauthenticatedExceptionHandler(HttpServletRequest request, UnauthenticatedException e) {

        logger.error("登录认证错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        return Result.error(ResultCode.USER_NOT_LOGGED_IN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result ExceptionHandler(HttpServletRequest request, Exception e) {

        logger.error("系统错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        return Result.error(ResultCode.SYSTEM_ERROR);
    }


}
