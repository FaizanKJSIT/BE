package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Listing
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Project listed_project;

    @ManyToOne
    private User lister;

    @Column(nullable = false, length = 10)
    private String date;
}
