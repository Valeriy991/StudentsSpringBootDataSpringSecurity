package com.valeriygulin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"num"})})

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String fio;

    @NonNull
    private int age;

    @NonNull
    private int num;

    @NonNull
    private double salary;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private List<Auto> autos;
}
