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
    private String message;
    private Object result;

    Result(int code,String message, Object data){
        this.code = code;
        this.message = message;
        this.result = data;
    }
}
