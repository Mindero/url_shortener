package org.example.exception;

public class UserExistException extends Exception{
    static final String msg = "User is already exist";
    public UserExistException(){super(msg);}
}
