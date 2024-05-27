package com.example.studentservice.service;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    


    public List<Student> GetAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }
    
   
    
    
    @Override
    public Student validateLogin(Student student) {
    	System.out.println("abhi");
    	 System.out.println("Received student data for authentication:");
         System.out.println("Email: " + student.getEmail());
         System.out.println("PRN: " + student.getPrn());
         System.out.println("Password: " + student.getPassword());
        Student userp = studentRepository.findByPrn(student.getPrn());
       // Student usere = studentRepository.findByEmail(student.getEmail());
        if (userp != null) {
            // Assuming user.getEmail() is the email and user.getPrn() is the PRN in the user object
            if ((userp.getPassword().equals(student.getPassword()) && userp.getPrn().equals(student.getPrn()))) {
                return userp;
            	
            }
        }
        return null;
    }

    
    
	@Override
	public String authenticateStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 

	    private String generateRandomNumericPassword() {
	        // Generate a random numeric string of length 8
	        return String.valueOf((int) ((Math.random() * 90000000) + 10000000));
	    }



	    @Autowired
	    private JavaMailSender mailSender;


public String sendHtmlEmail(String toEmail, String subject, String body) {
    System.out.println("Request coming: HTML email sending");
    try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("abhishekbhosale676@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true); // Set the second parameter to true to indicate HTML content

        mailSender.send(mimeMessage);

        System.out.println("HTML Email Sent...");
        return "Email sent to " + toEmail;
    } catch (Exception e) {
        System.err.println("Error sending HTML email: " + e.getMessage());
        return "Error sending email to " + toEmail;
    }
}

@Override
public String AddStudent(Student student) {
    System.out.println(student.getFirstname() + "  firstname");

    String password = generateRandomNumericPassword();
    System.out.println("password generated is :" + password);
    student.setPassword(password);

    // Generate PRN with the admission year
    GeneratedPrn generatedPrn = generatePrn();
    System.out.println("prn generated is :" + generatedPrn);

    // Set PRN, password, and admission year
    student.setPrn(generatedPrn.getPrn());
    student.setAdmissionYear(generatedPrn.getAdmissionYear());
    System.out.println("year admission generated is :" + generatedPrn.getAdmissionYear());

    studentRepository.save(student);

    // Send HTML email with styled content
    String emailBody = "<html><body style='font-family: Arial, sans-serif; color: #333;'>"
            + "<div style='background-color: #f4f4f4; padding: 20px;'>"
            + "<h2 style='color: #007BFF;'>Registration Successful</h2>"
            + "<p>Dear " + student.getFirstname() + ",</p>"
            + "<p>Congratulations! You have been successfully registered.</p>"
            + "<p style='font-weight: bold;'>Your PRN: " + generatedPrn.getPrn() + "</p>"
            + "<p style='font-weight: bold;'>Your Password: " + password + "</p>"
            + "<p>Please keep this information confidential.</p>"
            + "<p>Thank you!</p>"
            + "</div></body></html>";
    sendHtmlEmail(student.getEmail(), "Registration Successful", emailBody);

    return "Student added successfully. Registration details sent to " + student.getEmail();
}
	    
	    
		 public static GeneratedPrn generatePrn() {
		        // Get current date and time
		        LocalDateTime currentDateTime = LocalDateTime.now();

		        // Format: YYYYMMDDSS
		        String prn = String.format("%04d%02d%02d%02d%02d",
		                currentDateTime.getYear(),
		                currentDateTime.getMonthValue(),
		                currentDateTime.getDayOfMonth(),
		                currentDateTime.getHour(),
		                currentDateTime.getMinute());

		        int admissionYear = currentDateTime.getYear();

		        return new GeneratedPrn(Long.parseLong(prn), admissionYear);
		    }
		    
		    
		    // Class to hold generated PRN and admission year
		    static class GeneratedPrn {
		        private final Long prn;
		        private final int admissionYear;

		        public GeneratedPrn(Long prn, int admissionYear) {
		            this.prn = prn;
		            this.admissionYear = admissionYear;
		        }

		        public Long getPrn() {
		            return prn;
		        }

		        public int getAdmissionYear() {
		            return admissionYear;
		        }
		    }
		    
		    
		    public Student getStudentByPrn(String prn) {
		    	long prnValue = Long.parseLong(prn);
		    	System.out.println(prnValue);
		        return studentRepository.findByPrn(prnValue);
		    }
		    
		    
		    

		    public String sendSimpleEmail(String toEmail,String subject, String body)
		      {
		    	
		    	System.out.println("request coming function working");
		        SimpleMailMessage message = new SimpleMailMessage();
		        message.setFrom("abhishekbhosale676@gmail.com");
		        message.setTo(toEmail);
		        message.setText(body);
		        message.setSubject(subject);
		        mailSender.send(message);
		        System.out.println("Mail Send...");


		        return ("email sent to "+toEmail);
		    }


	    
		    
}
