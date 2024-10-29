package com.myprojects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class StudentsController {
    @Autowired
    private StudentsRepository repo;

    @GetMapping
    ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }

    @GetMapping("/status/{approved}")
    ResponseEntity<List<Student>> getStudentsApproved(@PathVariable Boolean approved){
        return ResponseEntity.status(HttpStatus.OK).body(repo.getStudentByApproved(approved));
    }

    @GetMapping("/age/{age}")
    ResponseEntity<List<Student>> getStudentsByAge(@PathVariable Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(repo.getStudentByAge(age));
    }

    @GetMapping("/age/{from}/{to}")
    ResponseEntity<List<Student>> getStudentsByAgeBetween(@PathVariable Integer from, @PathVariable Integer to){
        return ResponseEntity.status(HttpStatus.OK).body(repo.getStudentByAgeBetween(from, to));
    }

    @GetMapping("/{id}")
    ResponseEntity<Student> getStudentById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
    }

    @PutMapping
    ResponseEntity<Student> addStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(student));
    }

    @PutMapping("/{id}")
    ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student){
        if(!repo.existsById(id) || student.getId() != id)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(repo.save(student));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
