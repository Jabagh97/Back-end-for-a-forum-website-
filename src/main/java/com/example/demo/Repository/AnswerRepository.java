package com.example.demo.Repository;

import com.example.demo.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
}
