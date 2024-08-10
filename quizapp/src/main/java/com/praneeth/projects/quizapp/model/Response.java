package com.praneeth.projects.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {

    private Integer id;
    private String userAnswer;
}
