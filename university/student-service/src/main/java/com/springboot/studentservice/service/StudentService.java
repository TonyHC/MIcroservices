package com.springboot.studentservice.service;

import com.springboot.studentservice.entity.Student;
import com.springboot.studentservice.repository.StudentRepository;
import com.springboot.studentservice.request.CreateStudentRequest;
import com.springboot.studentservice.response.AddressResponse;
import com.springboot.studentservice.response.StudentResponse;
import com.springboot.studentservice.webclient.AddressWebClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AddressWebClient addressWebClient;

    public StudentService(StudentRepository studentRepository, AddressWebClient addressWebClient) {
        this.studentRepository = studentRepository;
        this.addressWebClient = addressWebClient;
    }

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());

        studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        return studentResponse;
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id).get();

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        return studentResponse;
    }

    private AddressResponse getAddressById(Long addressId) {
        Mono<AddressResponse> addressResponse = addressWebClient.webClient().get().uri("/" + addressId)
                .retrieve().bodyToMono(AddressResponse.class);

        return addressResponse.block();
    }
}