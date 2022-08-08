package com.springboot.universitymonolithic.services;

import com.springboot.universitymonolithic.entity.Address;
import com.springboot.universitymonolithic.entity.Student;
import com.springboot.universitymonolithic.repositories.AddressRepository;
import com.springboot.universitymonolithic.repositories.StudentRepository;
import com.springboot.universitymonolithic.request.CreateStudentRequest;
import com.springboot.universitymonolithic.response.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;

    public StudentService(StudentRepository studentRepository, AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
    }

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        addressRepository.save(address);

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddress(address);

        studentRepository.save(student);

        return new StudentResponse(student);
    }

    public StudentResponse getStudentById(Long id) {
        return new StudentResponse(studentRepository.findById(id).get());
    }
}