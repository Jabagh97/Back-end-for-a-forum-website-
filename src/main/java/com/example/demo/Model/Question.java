package com.example.demo.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "tags", nullable = false)
    private String tags;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "viewCount", nullable = false)
    private int viewCount;

    @Column(name = "voteCount", nullable = false)
    private int voteCount;

    @ManyToOne
    private User user;
}

