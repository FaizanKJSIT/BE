package com.DevConnect.BE.ExceptionH;

import java.util.List;

public class InvalidApplicationException extends RuntimeException
{
    private String message = "Failed to validate attributes: ";
    private String reason = "Reason: ";
    public InvalidApplicationException(List<Object> Attributes, String reason)
    {
        super("Failed to validate attributes: " + Attributes.toString()  + reason);
        this.message += Attributes.toString();
        this.reason = reason;
    }
}
