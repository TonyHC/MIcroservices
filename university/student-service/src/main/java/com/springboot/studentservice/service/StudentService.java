package com.springboot.studentservice.service;

import com.springboot.studentservice.entity.Student;
import com.springboot.studentservice.repository.StudentRepository;
import com.springboot.studentservice.request.CreateStudentRequest;
import com.springboot.studentservice.response.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CircuitBreakerService circuitBreakerService;

    public StudentService(StudentRepository studentRepository, CircuitBreakerService circuitBreakerService) {
        this.studentRepository = studentRepository;
        this.circuitBreakerService = circuitBreakerService;
    }

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());

        studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);

        studentResponse.setAddressResponse(circuitBreakerService.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);

        studentResponse.setAddressResponse(circuitBreakerService.getAddressById(student.getAddressId()));

        return studentResponse;
    }
}