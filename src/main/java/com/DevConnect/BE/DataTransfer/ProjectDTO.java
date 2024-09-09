package com.DevConnect.BE.DataTransfer;

import com.DevConnect.BE.Entity.User;
import java.util.List;

public class ProjectDTO
{
    private Integer id;
    private List<User> collaborators;
    private String name;
    private String aim;
    private String scope;
    private String domain;
    private String description;
    private String duration;
    private List<String> roles;
    private List<String> categories;
}
