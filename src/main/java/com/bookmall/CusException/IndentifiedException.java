package com.bookmall.CusException;

public class IndentifiedException extends Throwable {
    private String message;

    public IndentifiedException(String message) {
        this.message = message;
    }
}
