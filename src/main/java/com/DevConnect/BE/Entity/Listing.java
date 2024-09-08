package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Listing
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @OneToOne
    private Project listedProject;

    @ManyToOne
    private User Lister;

    @Column(nullable = false, length = 10)
    private String Date;
}
