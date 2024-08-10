package com.praneeth.projects.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Entity
public class Quiz {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true)
    private String title;

    @ManyToMany
    private List<Question> questions;
}
