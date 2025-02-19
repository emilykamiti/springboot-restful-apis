package com.sample.springboot.controller;

import com.sample.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http//localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                "nasinza",
                6,
                "joy"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header", "emi")
                .body(student);

    }

    //http//localhost:8080/students
    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Kamiti", 4, "emily"));
        students.add(new Student("Wabusa", 5, "Sam"));
        students.add(new Student("Olivia", 2, "Seleiyan"));
        students.add(new Student("Nick", 4, "shey"));
        return ResponseEntity.ok(students);
    }

    //spring boot rest api with path variable1
    //{id} - URI template variable
    //http://localhost:8080/students/1/kamy/nasinza

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(lastName, studentId, firstName);
        return ResponseEntity.ok(student);
    }

    //Spring boot Rest API with Request Param.
    // http://localhost:8080/students/query?id=1&firstName=emily&lastName=joy

    @GetMapping("students/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                                          @RequestParam String firstName,
                                                          @RequestParam String lastName) {
        Student student = new Student(lastName, id, firstName);
        return ResponseEntity.ok(student);
    }


    //Spring boot Rest API that handles HTTP POST Request
    //@PostMapping and @ RequestBody
    @PostMapping("students/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //Spring boot Rest API that handles HTTP PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    //spring boot Rest API that handles HTTP PUT Request - deleting the existing resource
    @DeleteMapping("students/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("student delete successfully!");
    }
}