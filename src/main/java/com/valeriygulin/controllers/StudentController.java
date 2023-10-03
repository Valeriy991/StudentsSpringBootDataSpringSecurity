package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.Student;
import com.valeriygulin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Student>> add(@RequestBody Student student) {
        try {
            this.studentService.addStudent(student);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Student>>> get() {
        return new ResponseEntity<>(new ResponseResult<>(null, this.studentService.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> get(@PathVariable long id) {
        try {
            Student student = this.studentService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<List<Student>>> get(@RequestParam String fio) {
        List<Student> student = this.studentService.get(fio);
        return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> delete(@PathVariable long id) {
        try {
            Student student = this.studentService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Student>> update(@RequestBody Student student) {
        try {
            if (student.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Student update = this.studentService.update(student);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}