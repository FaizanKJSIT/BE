package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    private List<User> collaborator;

    @Column(nullable = false)
    private String name;

    private String aim; //Aim of the project
    private String scope; //What will the project cover
    private String domain; //Domain of the project
    private String description; //Description of the project

    @ElementCollection
    private List<String> role; //Roles required in this project that someone will apply to

    @ElementCollection
    private List<String> category; //All Categories
}
