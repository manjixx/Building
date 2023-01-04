package com.eva.backend.result;

/**
 * Author：
 * Date：2022/12/1322:12
 * Desc:
 */
public enum ResultCode {

    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
