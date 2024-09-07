package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(nullable = false)
    private String Qualifications;

    //Need To add project id of project applied, roles applied for

    @Column(nullable = false, length = 10)
    private String Date;
}
