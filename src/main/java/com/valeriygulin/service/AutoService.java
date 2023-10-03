package com.valeriygulin.service;

import com.valeriygulin.model.Auto;

import java.util.List;

public interface AutoService {
    void addAuto(long studentId, Auto auto);

    List<Auto> get();

    Auto get(long id);

    List<Auto> get(String brand);

    Auto update(Auto auto);

    Auto delete(long id);

    List<Auto> getByStudentId(long id);
}
