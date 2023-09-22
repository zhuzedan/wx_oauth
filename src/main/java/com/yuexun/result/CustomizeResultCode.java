package com.yuexun.result;

/**
 * @author :zzd
 * @date : 2022/12/10
 */
public interface CustomizeResultCode {
    /**
     * 获取错误状态码
     * @return 错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();
}
