package com.kodhnk.base.core.utilities;


public class SuccessResult extends Result {
    public SuccessResult(String message, int statusCode) {
        super(true, message, statusCode);
    }
}