package com.DevConnect.BE.ExceptionH;

public class AuthenticateFailureException extends RuntimeException
{
    String username;
    public AuthenticateFailureException(String username)
    {
        super("Authentication failed for user: " + username);
        this.username = username;
    }
}
