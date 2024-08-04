package com.bookmall.CusException;

public class ShopCartEmptyException extends Exception{
    public String message;
    public ShopCartEmptyException(String msg){
        this.message = msg;
    }
}
