package com.example.demo.model.DTO;

import com.example.demo.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private String title;
    private String description;
    private LocalDateTime date;
    private int viewCount;
    private int voteCount;
    private List<String> tags = new ArrayList<>();

    public QuestionDTO(Question question) {
        this.title = question.getTitle();
        int descriptionLength = question.getDescription().length();
        this.description = question.getDescription().substring(0, Math.min(descriptionLength, 100));
        this.date = question.getDate();
        this.viewCount = question.getViewCount();
        this.voteCount = question.getVoteCount();
        question.getTags().forEach(tag -> this.tags.add(tag.getTitle()));
    }
}
