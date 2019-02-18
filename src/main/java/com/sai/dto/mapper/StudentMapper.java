package com.sai.dto.mapper;

import com.sai.dto.StudentDto;
import com.sai.model.Student;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toModel(StudentDto dto);

    StudentDto toDto(Student student);
}
