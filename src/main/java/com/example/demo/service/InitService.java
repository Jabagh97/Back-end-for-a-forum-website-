package com.example.demo.service;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.Tag;
import com.example.demo.model.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public void init() {
        User user = new User();
        user.setFirstName("Jabagh");
        user.setLastName("Anjouk");

        User user2 = new User();
        user2.setFirstName("Bill");
        user2.setLastName("Gates");

        user = userRepository.save(user);
        user2 = userRepository.save(user2);

        Question question = new Question();
        question.setTitle("How to configure port for a Spring Boot application");
        question.setDescription("How do I configure the TCP/IP port listened on by a Spring Boot application, so it does not use the default port of 8080.");
        question.setDate(LocalDateTime.now());
        question.setViewCount(2431);
        question.setVoteCount(927);
        question.setUser(user);

        Tag tag1 = new Tag("java");
        Tag tag2 = new Tag("spring");
        Tag tag3 = new Tag("server");
        Tag tag4 = new Tag("port");
        question.setTags(List.of(tag1, tag2, tag3, tag4));

        questionRepository.save(question);

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setDate(LocalDateTime.now());
        answer.setVoteCount(34);
        answer.setDescription("In case you are using application.yml add the Following lines to it\n" +
                "server:\n" +
                "     port: 9000\n" +
                "and of course 0 for random port.");
        answer.setUser(user2);
        answerRepository.save(answer);

        Question question2 = new Question();
        question2.setTitle("Spring Boot Adding Http Request Interceptors");
        question2.setDescription("What is the right way to add HttpRequest interceptors in spring boot application? What I want to do is log requests and responses for every http request.\n" +
                "\n" +
                "Spring boot documentation does not cover this topic at all. (http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)\n" +
                "\n" +
                "I found some web samples on how to do the same with older versions of spring, but those work with applicationcontext.xml. Please help.");
        question2.setDate(LocalDateTime.now().minusDays(3));
        question2.setViewCount(541);
        question2.setVoteCount(23);
        question2.setUser(user2);

        question2.setTags(List.of(tag1, tag2));

        questionRepository.save(question2);
    }
}
