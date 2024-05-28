package com.kodhnk.base.core.utilities;


import com.kodhnk.base.core.constant.Response;

public class Result {
    private boolean success;
    private String message;
    private int statusCode;

    public Result(boolean success, String message, int statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}