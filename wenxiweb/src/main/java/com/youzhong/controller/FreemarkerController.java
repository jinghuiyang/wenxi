package com.youzhong.controller;

import com.youzhong.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class FreemarkerController {
   public void templer(){
        try {
            Configuration cfg =new Configuration();
//			cfg.setClassForTemplateLoading(this.getClass(), "");
            cfg.setClassForTemplateLoading(this.getClass(), "../../../");

            Template template =cfg.getTemplate("test.ftl");

            HashMap<Object, Object> map = new HashMap<>();
            Student student1 = new Student();
            student1.setName("ss");
            student1.setSex("ee");
            Student student2 = new Student();
            student2.setName("hh");
            student2.setSex("tt");
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(student1);
            objects.add(student2);
            map.put("list",objects);
            map.put("name","sss");

            FileWriterWithEncoding fileWriterWithEncoding =new FileWriterWithEncoding(new File("D://html/user.html"), "UTF-8");


            template.process(map, fileWriterWithEncoding);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FreemarkerController().templer();
    }
}
