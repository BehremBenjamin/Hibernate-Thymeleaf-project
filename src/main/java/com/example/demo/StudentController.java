package com.example.demo;

import com.example.demo.Entities.Student;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String Students(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("clock", LocalDateTime.now());
        return "index";

    }

    @RequestMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "/addStudent";
    }

    @RequestMapping("/saved")
    public String saveStudent(Student student) {
        studentRepository.save(student);
        return "/saved";
    }
}
