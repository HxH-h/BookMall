package com.bookmall.CusException;

public class NumberIllegalException extends Exception{
    public String message;
    public NumberIllegalException(String msg){
        this.message = msg;
    }
}
