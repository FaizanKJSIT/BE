package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
public class Notification
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    private User receiver;

    private String type;

    @ElementCollection
    List<String> data;
}
