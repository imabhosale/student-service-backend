package com.example.studentservice.controller;


import com.example.studentservice.model.Student;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String AddStudents(@RequestBody Student student){
        System.out.println("call coming to controller for adding");
         return this.studentService.AddStudent(student);


    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List <Student>  Allstudents(){

        return this.studentService.GetAllStudents();
    }
    
    @Autowired
    private StudentService studentService1;


    @PostMapping("/login")
    public ResponseEntity<?> studentLogin(@RequestBody Student student) {
        Student authenticatedStudent = studentService.validateLogin(student);
        System.out.println("Data comming ");
        if (authenticatedStudent != null) {
            return ResponseEntity.ok(authenticatedStudent);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed!");
        }
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        try {
        	System.out.println("abhhi");
            // You might want to perform validation or other checks before saving
            studentService.AddStudent(student);
            return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @GetMapping("/details/{prn}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable String prn) {
        try {
        	System.out.println("getting details"+prn);
        	
            Student student = studentService.getStudentByPrn(prn);
            if (student != null) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle exceptions, log them, and return an appropriate response
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/check")
    public String checkmethod() {
    	return "student service getting ";
    }
    
    @GetMapping("/email")
	public String sendMail()
	{
	return	this.studentService.sendSimpleEmail("rushikeshpatil28055@gmail.com",
				"Account Created Successfully!..","this is body");
	}
    
    
    
}
