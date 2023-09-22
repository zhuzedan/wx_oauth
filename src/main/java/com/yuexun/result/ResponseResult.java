package com.yuexun.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @apiNote 封装统一返回值
 * @author zzd
 * @date 2023-02-26 15:49
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    @ApiModelProperty(value = "返回状态")
    private Boolean success;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    // 返回数据
    protected static <T> ResponseResult<T> build(T data) {
        ResponseResult<T> result = new ResponseResult<T>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> ResponseResult<T> build(T body, Integer code, String message) {
        ResponseResult<T> result = build(body);
        result.setSuccess(code == 200);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseResult<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResponseResult<T> result = build(body);
        result.setSuccess(resultCodeEnum.getCode() == 200);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> ResponseResult<T> success() {
        return ResponseResult.success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return build(data, ResultCodeEnum.SUCCESS.getCode(), message);
    }

    public static <T> ResponseResult<T> error() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static <T> ResponseResult<T> error(String message) {
        return build(null, ResultCodeEnum.FAIL.getCode(), message);
    }

    public static <T> ResponseResult<T> error(Integer code, String message) {
        return build(null, code, message);
    }

    public static <T> ResponseResult<T> error(ResultCodeEnum codeEnum) {
        return build(null, codeEnum);
    }
}

