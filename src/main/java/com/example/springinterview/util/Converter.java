package com.example.springinterview.util;

import com.example.springinterview.dto.StudentDto;
import com.example.springinterview.model.Student;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public StudentDto studentToDto(Student student){
        return new StudentDto(student.getId(), student.getName(), student.getAge());
    }
}
