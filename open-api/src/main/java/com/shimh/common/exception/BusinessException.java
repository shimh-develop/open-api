package com.shimh.common.exception;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3506744187536228284L;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BusinessException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException() {
    }
}
