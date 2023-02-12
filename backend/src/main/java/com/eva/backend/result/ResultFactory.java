package com.eva.backend.result;

/**
 * Author：
 * Date：2022/12/1322:13
 * Desc:
 */
public class ResultFactory {
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "成功", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }

    public static Result buildResult(ResultCode resultCode, String message) {
        return new Result(resultCode.code, message);
    }

    public static Result uploadSuccessResult(String message) {
        return buildResult(ResultCode.SUCCESS, message);
    }
}
