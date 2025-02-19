package com.sample.springboot.controller;

import com.sample.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http//localhost:8080/student
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student("nasinza", 6, "joy");
        return student;
    }

    //http//localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Kamiti", 4, "emily"));
        students.add(new Student("Wabusa", 5, "Sam"));
        students.add(new Student("Olivia", 2, "Seleiyan"));
        students.add(new Student("Nick", 4, "shey"));
        return students;
    }

    //spring boot rest api with path variable1
    //{id} - URI template variable
    //http://localhost:8080/students/1/kamy/nasinza

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(lastName, studentId, firstName);
    }

    //Spring boot Rest API with Request Param.
    // http://localhost:8080/students/query?id=1&firstName=emily&lastName=joy

    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        return new Student(lastName, id, firstName);
    }


    //Spring boot Rest API that handles HTTP POST Request
    //@PostMapping and @ RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student creatStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring boot Rest API that handles HTTP PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //spring boot Rest API that handles HTTP PUT Request - deleting the existing resource
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return "student delete successfully!";
    }
}