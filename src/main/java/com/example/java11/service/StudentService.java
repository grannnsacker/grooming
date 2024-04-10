package com.example.java11.service;



import com.example.java11.repository.GroupRepository;
import com.example.java11.entity.Group;
import com.example.java11.entity.Student;
import com.example.java11.repository.StudentRepository;
import com.example.java11.reqmodels.ReqStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class StudentService {
    public enum NameCode {
        FirstName,
        MiddleName,
        LastName
    }

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void addStudent(ReqStudent reqStudent){
        Group group = groupRepository.getById(reqStudent.getGroupId());
        Student student = Student.mapStudent(reqStudent, group);
        studentRepository.saveAndFlush(student);
        log.info("Add student {}", student);
//        emailService.sendMessage(student.toString());
    }

    @Transactional
    public Student getStudentByName(String name, NameCode nameCode) {
        log.info("Get student by %s name {}".formatted(nameCode), name);
        switch (nameCode){
            case FirstName -> {return studentRepository.findAllByFirstName(name).get(0);}
            case MiddleName -> {return studentRepository.findAllByMiddleName(name).get(0);}
            case LastName -> {return studentRepository.findAllByLastName(name).get(0);}
        }
        return null;
    }

    @Transactional
    public void deleteStudentById(Long id){
        log.info("Delete student by id : {}", id);
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student getStudentById(Long id){
        log.info("Get student by id : {}", id);
        return studentRepository.getReferenceById(id);
    }

    @Transactional
    public List<Student> getAllStudents(){
        log.info("Get all students");
        return studentRepository.findAll();
    }

}
