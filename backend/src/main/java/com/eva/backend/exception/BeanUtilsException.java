package com.eva.backend.exception;

/**
 * Author：
 * Date：2023/1/1811:33
 * Desc:
 */
public class BeanUtilsException extends RuntimeException{
    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}
