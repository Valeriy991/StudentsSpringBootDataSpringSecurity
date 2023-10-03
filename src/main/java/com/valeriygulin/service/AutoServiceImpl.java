package com.valeriygulin.service;

import com.valeriygulin.model.Auto;
import com.valeriygulin.model.Student;
import com.valeriygulin.repository.AutoRepository;
import com.valeriygulin.repository.StudentRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AutoServiceImpl implements AutoService {

    private AutoRepository autoRepository;
    private StudentService studentService;

    @Autowired
    public void setAutoRepository(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @Autowired
    public AutoServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void addAuto(long studentId, Auto auto) {
        try {
            Student student = studentService.get(studentId);
            auto.setStudent(student);
            this.autoRepository.save(auto);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Auto is already exist!");
        }
    }

    @Override
    public List<Auto> get() {
        return this.autoRepository.findAll();
    }

    @Override
    public Auto get(long id) {
        return this.autoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Auto doesn't exist!"));
    }

    @Override
    public List<Auto> get(String brand) {
        return this.autoRepository.findByBrand(brand);
    }

    @Override
    public Auto update(Auto auto) {
        Auto fromBase = get(auto.getId());
        fromBase.setBrand(auto.getBrand());
        fromBase.setPower(auto.getPower());
        fromBase.setYear(auto.getYear());
        try {
            this.autoRepository.save(fromBase);
            return fromBase;
        } catch (Exception e) {
            throw new IllegalArgumentException("Auto is already exists!");
        }
    }

    @Override
    public Auto delete(long id) {
        Auto auto = this.get(id);
        this.autoRepository.deleteById(id);
        return auto;
    }

    @Override
    public List<Auto> getByStudentId(long id) {
        List<Auto> res = new ArrayList<>();
        List<Auto> all = this.autoRepository.findAll();
        for (Auto auto : all) {
            if (auto.getStudent().getId() == id) {
                res.add(auto);
            }
        }
        return res;
    }
}
