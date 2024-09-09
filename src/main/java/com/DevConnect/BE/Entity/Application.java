package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private User applicant;

    @OneToOne
    private Project applied_project;

    @Column(nullable = false, length = 10)
    private String date;
}
