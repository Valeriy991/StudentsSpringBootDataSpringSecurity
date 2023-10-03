package com.valeriygulin.service;

import com.valeriygulin.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    List<Student> get();
    Student get(long id);

    List<Student> get(String fio);

    Student update(Student student);
    Student delete(long id);

}
