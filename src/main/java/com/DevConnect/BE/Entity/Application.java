package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @ManyToOne
    private User Applicant;

    @OneToOne
    private Project AppliedProject;

    @Column(nullable = false, length = 10)
    private String Date;
}
