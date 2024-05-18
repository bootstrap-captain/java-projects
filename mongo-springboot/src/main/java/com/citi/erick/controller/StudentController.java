package com.citi.erick.controller;

import com.citi.erick.entity.Student;
import com.citi.erick.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class StudentController {


    @Autowired
    private StudentService service;

    @PostMapping("/insert")
    public Student insertOne(Student student) {
        return service.insertOne(student);
    }

    @GetMapping("/queryAll")
    public List<Student> queryAll() {
        return service.queryAll();
    }

    @GetMapping("/queryByStatus")
    public List<Student> queryByStatus(boolean status) {
        return service.queryByStatus(status);
    }

    @GetMapping("/updateStatus")
    public void update(String id, boolean status) {
        service.updateStatus(id, status);
    }
}
