package com.shimh.common.base;

import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
public class Result implements Serializable {

    private Integer error_code;

    private String error_message;

    private Object data;

    public Result() {
    }

    public Result(Integer error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setError_code(Base.ERROR_CODE);
        result.setError_message(msg);
        return result;
    }

    public static Result error(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.error_code = code.code();
        this.error_message = code.message();
    }


    public Map<String, Object> simple() {
        Map<String, Object> simple = new HashMap<String, Object>();
        this.data = simple;

        return simple;
    }


    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}