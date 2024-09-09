package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Listing
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Project listed_project;

    @ManyToOne
    private User lister;

    @Column(nullable = false, length = 10)
    private String date;
}
