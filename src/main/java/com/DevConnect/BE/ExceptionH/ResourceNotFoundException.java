package com.DevConnect.BE.ExceptionH;

public class ResourceNotFoundException extends RuntimeException
{
    String resource_name;
    String field_name;
    String field_value;
    public ResourceNotFoundException(String resource_name, String field_name, String field_value)
    {
        super(resource_name + " is not found with " + field_name + ": " + field_value);
        this.resource_name = resource_name;
        this.field_name = field_name;
        this.field_value = field_value;
    }
}
