package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User applicant;

    @OneToOne
    private Project applied_project;

    private String applied_role;

    private String status;

    @Column(nullable = false, length = 10)
    private String date;
}
