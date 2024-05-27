package com.example.studentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Student {

	@Id
	@Column(name = "prn")
	private Long prn;
	private String password;
	private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String department;
    private String gender;
    private int age;
    private int semester;
    private String address;
    
    private String mobileNumber;
    private String nationality;
    private Integer admissionYear;
    
 // Additional field for password reset token
   // private String resetToken;
	

   
}
