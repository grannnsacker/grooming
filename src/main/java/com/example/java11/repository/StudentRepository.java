package com.example.java11.repository;


import com.example.java11.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findAllByFirstName(String firstName);
    List<Student> findAllByMiddleName(String middleName);
    List<Student> findAllByLastName(String lastName);

}
