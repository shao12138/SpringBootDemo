package com.nudt.h2db.Controller;

import com.nudt.h2db.Entity.Student;
import com.nudt.h2db.Mapper.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("/student")
@Controller()
public class StudentController {

    @Resource
    StudentRepository studentRepository;


    @ResponseBody
    @RequestMapping("/")
    public String root(){
        Student student= new Student(2,"小明", 18);
        studentRepository.save(student);
        System.out.println(student);
        return student.toString();
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Student> s = studentRepository.findById(id);
        studentRepository.deleteById(id);
        return s.get().toString() + "已经被删除";
    }

    @ResponseBody
    @RequestMapping("/update/{id}/{name}")
    public String update(@PathVariable Integer id, @PathVariable String name){
        /*获取以后，再重新赋值 其实有其他更好的办法*/
        Student student = studentRepository.findById(id).get();
        student.setName(name);
        studentRepository.save(student);
        return student.toString();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public String find(@PathVariable Integer id) {
        Optional<Student> s = studentRepository.findById(id);
        return s.get().toString();
    }
    @ResponseBody
    @RequestMapping("/findAll")
    public void findAll() {
        ArrayList<Student> s = (ArrayList<Student>) studentRepository.findAll();
        for (int i =0;i<s.size();i++){
            System.out.println(s.get(i).getName());
        }
    }
}