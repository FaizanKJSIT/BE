package com.DevConnect.BE.DataTransfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ProjectDTO
{
    private Long id;
    private List<String> collaborator;
    private String name;
    private String aim;
    private String status;
    private String privacy_status;
    private String scope;
    private String domain;
    private String description;
    private List<String> role;
    private List<String> category;
}
