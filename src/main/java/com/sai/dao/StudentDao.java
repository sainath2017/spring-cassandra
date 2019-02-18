package com.sai.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.sai.model.Student;

@Repository
public interface StudentDao extends CassandraRepository<Student, Long> {

    @AllowFiltering
    @Consistency(ConsistencyLevel.LOCAL_ONE)
    List<Student> findStudentsByFirstName(String firstName);

}
