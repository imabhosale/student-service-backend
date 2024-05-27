package com.example.studentservice.service;

import com.example.studentservice.model.Student;

import java.util.List;

public interface StudentService {
    public String  AddStudent(Student student);

    public List<Student> GetAllStudents();
    String authenticateStudent(Student student);
    public Student validateLogin(Student student);
   
    public Student getStudentByPrn(String prn);
    public String sendSimpleEmail(String toEmail,String subject, String body);
    
}
