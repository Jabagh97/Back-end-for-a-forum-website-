package com.example.demo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionRequest {

    private String title;
    private String description;
    private List<String> tags = new ArrayList<>();
    private Long userId;
}
