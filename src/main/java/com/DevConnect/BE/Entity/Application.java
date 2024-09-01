package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(nullable = false)
    private String Qualifications;

    @Column(nullable = false, length = 10)
    private String Date;
}
