package com.example.java11.contollers;

import com.example.java11.entity.Student;
import com.example.java11.reqmodels.ReqStudent;
import com.example.java11.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class StudentControllers {
    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String home(){
        return "index.html";
    }



    @GetMapping("/get_student_by_id")
    @ResponseBody
    public Student getStudentById(@RequestParam Long id){
        return studentService.getStudentById(id);
    }


    @GetMapping("/get_student_by_first_name")
    @ResponseBody
    public Student getStudentByFirstName(@RequestParam String name){
        return studentService.getStudentByName(name, StudentService.NameCode.FirstName);
    }

    @GetMapping("/get_student_by_middle_name")
    @ResponseBody
    public Student getStudentByMiddleName(@RequestParam String name){
        return studentService.getStudentByName(name, StudentService.NameCode.MiddleName);
    }

    @GetMapping("/get_student_by_last_name")
    @ResponseBody
    public Student getStudentByLastName(@RequestParam String name){
        return studentService.getStudentByName(name, StudentService.NameCode.LastName);
    }

    @GetMapping("/delete_student_by_id")
    @ResponseBody
    public String deleteStudentByFirstName(@RequestParam Long id){
        studentService.deleteStudentById(id);
        return "Success";
    }

    @PostMapping("/post_student")
    @ResponseBody
    public String postStudent(@RequestBody ReqStudent reqStudent){
        studentService.addStudent(reqStudent);
        return "Success";
    }

    @GetMapping("/get_all_students")
    @ResponseBody
    public List<Student> getAllStudent(){
        return studentService.getAllStudents();
    }

}
