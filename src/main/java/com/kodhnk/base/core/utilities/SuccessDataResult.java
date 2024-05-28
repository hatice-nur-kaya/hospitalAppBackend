package com.kodhnk.base.core.utilities;


public class SuccessDataResult<T> extends DataResult<T> {
    public SuccessDataResult(String message, T data, int statusCode) {
        super(true, message, data, statusCode);
    }
}