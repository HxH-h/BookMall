package com.bookmall.CusException;

public class OrderNotFoundException extends Exception{
    public String message;
    public OrderNotFoundException(String msg){
        this.message = msg;
    }
}
