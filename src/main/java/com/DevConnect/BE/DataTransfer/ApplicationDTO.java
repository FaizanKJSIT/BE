package com.DevConnect.BE.DataTransfer;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDTO
{
    private Long id;

    private String applicant;

    private Long applied_project;

    private String applied_role;

    private String status;

    private String date;
}
