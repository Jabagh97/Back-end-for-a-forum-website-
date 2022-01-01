package com.example.demo.model.DTO;

import com.example.demo.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private String description;
    private LocalDateTime date;
    private int voteCount;

    public AnswerDTO(Answer answer) {
        this.description = answer.getDescription();
        this.date = answer.getDate();
        this.voteCount = answer.getVoteCount();
    }
}
