package com.sai.controller;

import com.sai.dto.StudentDto;
import com.sai.model.Student;
import com.sai.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1-student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*
     * This Method is executed after dependency injection is done to perform initialization of sample data
     */
    @PostConstruct
    public void saveStudents() {
        studentService.saveStudents();
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<StudentDto>> getStudentDetails() {
        return new ResponseEntity<>(studentService.getStudentDetails(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDto dto) {
        return new ResponseEntity<>(studentService.createStudent(dto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody StudentDto dto) {
        return new ResponseEntity<>(studentService.updateStudent(dto), HttpStatus.OK);
    }

    @PostMapping(value = "/filter-by-first-name")
    public ResponseEntity<List<StudentDto>> filterByFirstName(@RequestParam(name = "firstName") String firstName) {
        return new ResponseEntity<>(studentService.filterByFirstName(firstName), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
