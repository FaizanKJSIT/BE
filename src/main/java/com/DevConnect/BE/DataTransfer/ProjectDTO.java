package com.DevConnect.BE.DataTransfer;

import com.DevConnect.BE.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ProjectDTO
{
    private Integer id;
    private List<User> collaborator;
    private String name;
    private String aim;
    private String scope;
    private String domain;
    private String description;
    private String duration;
    private List<String> role;
    private List<String> category;
}
