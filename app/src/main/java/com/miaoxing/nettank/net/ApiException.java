package com.miaoxing.nettank.net;

/**
 *
 * @Date : 2018/5/24
 * @Desc : 网络请求异常
 */
public class ApiException extends RuntimeException {

    public String code;
    public String message;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
