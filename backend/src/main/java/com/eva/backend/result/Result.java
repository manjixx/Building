package com.eva.backend.result;

import lombok.Data;

/**
 * Author：
 * Date：2022/12/620:40
 * Desc:
 */
@Data
public class Result {
    //响应码
    private int code;

    public Result(int code) {
        this.code = code;
    }
}
