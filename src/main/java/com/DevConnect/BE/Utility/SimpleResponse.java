package com.DevConnect.BE.Utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleResponse
{
    private String message;
    private boolean success;
}
