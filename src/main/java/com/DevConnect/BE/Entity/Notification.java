package com.DevConnect.BE.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Notification
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    private User receiver;

    private String type;

    @ElementCollection
    List<String> Data;
}
