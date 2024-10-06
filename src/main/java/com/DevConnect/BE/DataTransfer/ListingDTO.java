package com.DevConnect.BE.DataTransfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListingDTO
{
    private Long id;
    private ProjectDTO listed_project;
    private String lister;
    private String date;
}
