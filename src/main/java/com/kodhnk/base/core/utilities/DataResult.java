package com.kodhnk.base.core.utilities;


public class DataResult<T> extends Result {
    private T data;

    public DataResult(boolean success, String message, T data, int statusCode) {
        super(success, message, statusCode);
        this.data = data;
    }


    public T getData() {
        return this.data;
    }
}