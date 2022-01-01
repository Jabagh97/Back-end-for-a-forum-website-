package com.example.demo.model;

import com.example.demo.controller.model.Vote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "answers")
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Column(name = "vote_count", nullable = false)
    private int voteCount;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;


    public void vote(Vote vote) {
        if (Vote.UP_VOTE.equals(vote)) {
            this.voteCount++;
        } else {
            this.voteCount--;
        }
    }
}
