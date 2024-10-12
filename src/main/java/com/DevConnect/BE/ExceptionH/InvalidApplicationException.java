package com.DevConnect.BE.ExceptionH;

import lombok.Getter;

import java.util.List;

@Getter
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
