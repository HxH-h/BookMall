package com.bookmall.CusException;

public class AddressNotFoundException extends Exception{
    public String message;
    public AddressNotFoundException (String msg){
        this.message = msg;
    }
}
