package org.example.exception;

public class LogoutException extends Exception{
    static final String msg = "Login/Register first";
    public LogoutException(){super(msg);}
}
