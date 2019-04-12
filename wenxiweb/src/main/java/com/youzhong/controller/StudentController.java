package com.youzhong.controller;

import com.youzhong.entity.Student;
import com.youzhong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("list")
    public String list(Model model){
     List<Student> list= studentService.find();
        model.addAttribute("list",list);

        return "index";
    }


}



