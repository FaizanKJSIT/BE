package com.DevConnect.BE.DataTransfer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class UserDTO
{
    private String username;
    private String password;
    private String first_name;
    private String middle_name;
    private String last_name;
    private List<String> email_id;
    private List<Long> mobile_no;
    private String qualifications;
    private String domain_interest;
}
