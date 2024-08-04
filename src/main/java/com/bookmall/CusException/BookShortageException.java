package com.bookmall.CusException;

public class BookShortageException extends Exception{
    public String message ;
    public BookShortageException(String msg){
        this.message = msg;
    }
}
