package com.example.demo.controller;

import com.example.demo.controller.model.*;
import com.example.demo.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public GetQuestionsResponse getAllQuestions(@RequestParam(required = false) List<String> tags) {
        return questionService.getAllQuestions(tags);
    }

    @GetMapping("/{id}")
    public GetQuestionResponse getQuestionDetails(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    @PostMapping
    public Long createQuestion(@RequestBody CreateQuestionRequest request) {
        return questionService.createQuestion(request);
    }

    @PostMapping("/{questionId}/answers")
    public AnswerQuestionResponse answerQuestion(@RequestBody AnswerQuestionRequest request, @PathVariable Long questionId) {
        return questionService.answerQuestion(request, questionId);
    }

    @PutMapping("/{questionId}/vote")
    public VoteResponse voteQuestion(@RequestBody VoteRequest request, @PathVariable Long questionId) {
        return questionService.voteQuestion(request.getVote(), questionId);
    }

    @PutMapping("/{questionId}/answers/{answerId}")
    public AnswerQuestionResponse updateAnswer(@RequestBody UpdateAnswerRequest request, @PathVariable Long questionId, @PathVariable Long answerId) {
        return questionService.updateAnswer(request, questionId, answerId);
    }

    @PutMapping("/{questionId}/answers/{answerId}/vote")
    public VoteResponse voteAnswer(@RequestBody VoteRequest request, @PathVariable Long questionId, @PathVariable Long answerId) {
        return questionService.voteAnswer(request.getVote(), questionId, answerId);
    }
}
