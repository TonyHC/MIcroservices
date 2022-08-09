package com.springboot.studentservice.controller;

import com.springboot.studentservice.request.CreateStudentRequest;
import com.springboot.studentservice.response.StudentResponse;
import com.springboot.studentservice.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
}