package com.shimh.common.constant;

import org.omg.CORBA.DynAnyPackage.Invalid;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(Base.SUCCESS_CODE, "成功"),
    ERROR(Base.ERROR_CODE, "失败"),

    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_REGISTER_ERROR(20006, "用户注册错误"),
    PASSWORD_ERROR(20006, "原密码错误"),

    SYSTEM_ERROR(30001, "服务异常"),

    NO_ACCESS_PERMISSION(70001, "没有访问权限"),
    INVALID_TOKEN(70002, "无效Token"),
    SESSION_TIMEOUT(70003, "会话超时");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
