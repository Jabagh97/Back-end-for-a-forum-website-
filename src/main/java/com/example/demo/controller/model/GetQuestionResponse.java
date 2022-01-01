package com.example.demo.controller.model;

import com.example.demo.model.DTO.AnswerDTO;
import com.example.demo.model.DTO.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionResponse {

    private QuestionDTO question;
    private List<AnswerDTO> answers;
}
