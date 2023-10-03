package com.valeriygulin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "autos", uniqueConstraints = {@UniqueConstraint(columnNames = {"brand", "power", "year"})})
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String brand;

    @NonNull
    private int power;

    @NonNull
    private int year;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;
}
