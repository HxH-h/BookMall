package com.bookmall.CusException;

public class BookNotFoundException extends Exception{
    public String message;

    public BookNotFoundException(String message) {
        this.message = message;
    }

}
