package com.valeriygulin.service;

import com.valeriygulin.model.Student;
import com.valeriygulin.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {
        try {
            this.studentRepository.save(student);
        } catch (Exception e) {
            throw new IllegalArgumentException("Student has already added!");
        }
    }

    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student doesn't exist!"));
    }

    @Override
    public List<Student> get(String fio) {
        return this.studentRepository.findByFio(fio);
    }


    @Override
    public Student update(Student student) {
        Student fromBase = this.get(student.getId());
        fromBase.setFio(student.getFio());
        fromBase.setAge(student.getAge());
        fromBase.setNum(student.getNum());
        fromBase.setSalary(student.getSalary());

        try {
            this.studentRepository.save(fromBase);
            return fromBase;
        } catch (Exception e) {
            throw new IllegalArgumentException("Student is already exist!");
        }
    }

    @Override
    public Student delete(long id) {
        Student student = this.get(id);
        this.studentRepository.deleteById(id);
        return student;
    }


}
