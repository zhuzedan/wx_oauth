package com.yuexun.result;

/**
 * @author :zzd
 * @apiNote :接口状态枚举
 * @date : 2022/12/10
 */
public enum ResultCodeEnum implements CustomizeResultCode {
    //成功失败
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),

    //认证鉴权
    UNAUTHORIZED(401, "登录认证失败，请重新登录"),
    FORBIDDEN(403, "权限不足"),

    //参数错误
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1005, "参数缺失"),
    NAME_EXISTED(1005,"名称已存在"),
    CREATE_FAIL(10011, "新增失败"),
    DELETE_FAIL(10013, "删除失败"),

    //用户错误
    ACCOUNT_ERROR(2001, "用户名不正确"),
    PASSWORD_ERROR(2002, "密码不正确"),
    LOGIN_ERROR(2003, "用户名或密码不正确"),
    ACCOUNT_STOP(2004, "账号已停用");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
