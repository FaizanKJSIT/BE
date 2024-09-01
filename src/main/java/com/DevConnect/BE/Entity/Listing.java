package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

@Entity
public class Listing
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String Details;

    @Column(nullable = false)
    private String Domain;

    @Column(nullable = false, length = 10)
    private String Date;
}
