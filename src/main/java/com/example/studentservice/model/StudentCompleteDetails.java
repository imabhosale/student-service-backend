package com.example.studentservice.model;
import jakarta.persistence.*;
import lombok.*;

//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class StudentCompleteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FatherName;
    private String MotherName;
    private String Occupation;

    private String Gender;
    private String Address;
    private String Mobile;
}
