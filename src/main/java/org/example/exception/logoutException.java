package org.example.exception;

public class logoutException extends Exception{
    static final String msg = "You are already logout";
    public logoutException(){super(msg);}
}
