package com.example.springinterview.controller;

import com.example.springinterview.dto.StudentDto;
import com.example.springinterview.exceptions.DataValidationException;
import com.example.springinterview.exceptions.ResourceNotFoundException;
import com.example.springinterview.model.Student;
import com.example.springinterview.service.StudentService;
import com.example.springinterview.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final Converter converter;
    private final StudentService studentService;

    @GetMapping
    public Stream<StudentDto> findAll(){
        return studentService.findAll().stream().map(s -> converter.studentToDto(s));
    }

    @PostMapping
    public StudentDto save(@RequestBody @Validated StudentDto studentDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        studentService.save(student);
        return converter.studentToDto(student);
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        Student s = studentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student id = " + id + " not found"));
        return converter.studentToDto(s);
    }

    @PutMapping
    public void updateStudent(@RequestBody StudentDto studentDto) {
        studentService.updateStudentFromDto(studentDto);
    }

    @DeleteMapping
    public void delete(@RequestBody StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        studentService.delete(student);

    }


}
