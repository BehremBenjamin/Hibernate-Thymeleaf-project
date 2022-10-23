package com.example.demo;

import com.example.demo.Entities.Student;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Optional;


@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping({"/index", "/"})
    public ModelAndView getAllStudents() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("students", studentRepository.findAll());
        return mav;
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

    @GetMapping("/deleteStudent/{id}")
    public String delete( @PathVariable (value = "id") long id, Model model) throws SQLException {
        Optional<Student> deletedStudent = studentRepository.findById(id);
        if (deletedStudent.isPresent()) {
            model.addAttribute("deletedStudent", deletedStudent.get());
        } else {
            throw new SQLException();
        }
        studentRepository.deleteById(id);
        return "/deleted";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(
            @PathVariable (value = "id") long id, Model model) {
        Student student = studentRepository.getReferenceById(id);
        model.addAttribute("student", student);
        return "/updateStudent";
    }

    @RequestMapping("/updated")
    public String updated(Student student){
        return "/updated";
    }


}
