package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(nullable = false)
    private String Name;


    private String Aim;
    private String Scope;
    private String Duration;
    private String Roles;
    private String Description;
}
