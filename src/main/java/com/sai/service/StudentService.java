package com.sai.service;

import com.sai.dao.StudentDao;
import com.sai.dto.StudentDto;
import com.sai.dto.mapper.StudentMapper;
import com.sai.exception.EntityNotFoundException;
import com.sai.model.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

	@Autowired
	private static Logger log = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private StudentMapper mapper;

	public List<StudentDto> getStudentDetails() {
		List<Student> students = studentDao.findAll();
		return students.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public Student createStudent(StudentDto dto) {
		Student student = mapper.toModel(dto);
		return studentDao.save(student);
	}

	public void deleteStudent(Long id) {
		studentDao.deleteById(id);
	}

	public Student updateStudent(StudentDto dto) {
		Optional<Student> studentOptional = studentDao.findById(dto.getId());
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get();
			student.setFirstName(dto.getFirstName());
			student.setLastName(dto.getLastName());
			return studentDao.save(student);
		} else {
			log.error("Entiy is not found for dto {} ", dto);
			throw new EntityNotFoundException("Entity Not Found");
		}
	}

	public void saveStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1L, "Suresh", "M"));
		students.add(new Student(2L, "Kumar", "N"));
		students.add(new Student(3L, "Naveen", "P"));
		studentDao.saveAll(students);
	}

	public List<StudentDto> filterByFirstName(String firstName) {
		List<Student> students = studentDao.findStudentsByFirstName(firstName);
		if (students.isEmpty()) {
			log.error("Entiy is not found for firstName = {} ", firstName);
			throw new EntityNotFoundException("Entity Not Found");
		} else {
			return students.stream().map(mapper::toDto).collect(Collectors.toList());
		}

	}
}
