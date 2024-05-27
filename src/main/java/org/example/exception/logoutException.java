package org.example.exception;

public class logoutException extends Exception{
    static final String msg = "Login/Register first";
    public logoutException(){super(msg);}
}
