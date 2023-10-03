package com.valeriygulin.repository;

import com.valeriygulin.model.Auto;
import com.valeriygulin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto,Long> {
    List<Auto> findByBrand(String brand);
}
