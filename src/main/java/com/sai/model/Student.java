package com.sai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

/*
 * @author Sainath
 */
@Table(value = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
}
