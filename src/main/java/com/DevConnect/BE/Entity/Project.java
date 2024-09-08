package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @ManyToMany
    private List<User> Collaborators;

    @Column(nullable = false)
    private String Name;

    private String Aim; //Aim of the project
    private String Scope; //What will the project cover
    private String Domain; //Domain of the project
    private String Description; //Description of the project
    private String Duration;  //How long is someone expected to be working on this project

    @ElementCollection
    private List<String> Roles; //Roles required in this project that someone will apply to

    @ElementCollection
    private List<String> Categories; //All Categories
}
