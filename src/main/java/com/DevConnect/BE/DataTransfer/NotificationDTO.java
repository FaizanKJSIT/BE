package com.DevConnect.BE.DataTransfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationDTO
{
    private Long id;
    private String message;
    private String receiver;
    private String type;
    List<String> data;
}
