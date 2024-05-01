package org.example.exception;

public class UserPasswordIncorrect extends Exception{
    static final String msg = "Login or password is incorrect";
    public UserPasswordIncorrect(){super(msg);}
}
