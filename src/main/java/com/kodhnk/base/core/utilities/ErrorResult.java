package com.kodhnk.base.core.utilities;

import com.kodhnk.base.core.constant.Response;


public class ErrorResult extends Result {

    public ErrorResult(String message, int statusCode) {
        super(false, message, statusCode);
    }
}