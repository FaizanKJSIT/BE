package com.DevConnect.BE.ExceptionH;

public class AlreadyExistsException extends RuntimeException
{
    String entity;
    String primaryKey;
    String message;
    public AlreadyExistsException(String Entity, String PrimaryKey)
    {
        super(Entity + ": " + PrimaryKey + " Already Exists!");
        this.entity = Entity;
        this.primaryKey = PrimaryKey;
        this.message = Entity + ": " + PrimaryKey + " Already Exists!";
    }
}
