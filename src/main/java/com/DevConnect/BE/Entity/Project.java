package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    private List<User> collaborators;

    @Column(nullable = false)
    private String name;

    private String aim; //Aim of the project
    private String scope; //What will the project cover
    private String domain; //Domain of the project
    private String description; //Description of the project
    private String duration;  //How long is someone expected to be working on this project

    @ElementCollection
    private List<String> roles; //Roles required in this project that someone will apply to

    @ElementCollection
    private List<String> categories; //All Categories
}
