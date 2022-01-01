package com.example.demo.model;

import com.example.demo.controller.model.CreateQuestionRequest;
import com.example.demo.controller.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "vote_count", nullable = false)
    private int voteCount;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tag> tags;

    @ManyToOne
    private User user;

    public Question(CreateQuestionRequest request, List<Tag> tags, User user) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.date = LocalDateTime.now();
        this.viewCount = 0;
        this.voteCount = 0;
        this.tags = tags;
        this.user = user;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void vote(Vote vote) {
        if (Vote.UP_VOTE.equals(vote)) {
            this.voteCount++;
        } else {
            this.voteCount--;
        }
    }
}

