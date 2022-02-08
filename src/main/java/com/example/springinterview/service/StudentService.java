package com.example.springinterview.service;


import com.example.springinterview.dto.StudentDto;
import com.example.springinterview.exceptions.ResourceNotFoundException;
import com.example.springinterview.model.Student;
import com.example.springinterview.repository.StudentRepository;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student>  findAll(){
        return studentRepository.findAll();
    }
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Student student){
      studentRepository.delete(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudentFromDto(StudentDto studentDto){
        Student student = findById(studentDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Student id = " + studentDto.getId() + " not found"));
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
    }

}
