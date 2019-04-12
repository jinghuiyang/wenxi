package com.youzhong.service.impl;

import com.youzhong.dao.StudentMapper;
import com.youzhong.entity.Student;
import com.youzhong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> find() {
        return studentMapper.selectByExample(null);
    }
}
