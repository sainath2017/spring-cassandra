package com.sai.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.sai.model.Student;

@Repository
public interface StudentDao extends CassandraRepository<Student, Long> {
	
	/*
	 * It's not a good practice to use allow filtering, Here I just used it for example purpose.
	 * Try to index if it's needed when Data modeling
	 */

    @AllowFiltering
    @Consistency(ConsistencyLevel.LOCAL_ONE)
    List<Student> findStudentsByFirstName(String firstName);
    
    @Consistency(ConsistencyLevel.LOCAL_ONE)
    @Query("select * from Student where lastName = ?0 allow filtering")
    Iterable<Student> searchByLastName(String lastName);

}
