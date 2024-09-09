package com.DevConnect.BE.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class User
{
    @Id
    @Column(nullable = false)
    @Size(min=10)
    private String username;

    @Column(nullable = false)
    @Size(min=8)
    private String password;

    @Column(nullable = false)
    private String first_name;
    private String middle_name;
    private String last_name;

    @ElementCollection
    private List<String> email_id;

    @ElementCollection
    private List<Long> mobile_no;

    @ElementCollection
    private List<String> interest;

    private String qualification;
}
