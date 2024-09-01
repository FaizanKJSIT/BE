package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class User
{
    @Id
    @Column(nullable = false, length = 10)
    private String username;

    @Column(nullable = false, length = 8)
    private String password;

    @Column(nullable = false)
    private String first_name;
    private String middle_name;
    private String last_name;

    @Column(nullable = false)
    private String email_id;

    private long mobile_no;
    
    private String qualifications;

    private String domain_interest;
}
