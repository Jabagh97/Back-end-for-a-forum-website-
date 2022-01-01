package com.example.demo.service;

import com.example.demo.controller.model.*;
import com.example.demo.model.Answer;
import com.example.demo.model.DTO.AnswerDTO;
import com.example.demo.model.DTO.QuestionDTO;
import com.example.demo.model.Question;
import com.example.demo.model.Tag;
import com.example.demo.model.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;
    private final TagService tagService;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, UserService userService, TagService tagService, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.userService = userService;
        this.tagService = tagService;
        this.answerRepository = answerRepository;
    }

    public GetQuestionsResponse getAllQuestions(List<String> tags) {
        List<Question> questions = questionRepository.findAll();
        if (!questions.isEmpty()) {
            List<QuestionDTO> questionDTOS = questions.stream()
                    .map(QuestionDTO::new)
                    .collect(Collectors.toList());

            return new GetQuestionsResponse(questionDTOS);
        }

        return new GetQuestionsResponse(Collections.emptyList());
    }

    public GetQuestionResponse getQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question Not Found with id:" + id));

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTitle(question.getTitle());
        questionDTO.setDescription(question.getDescription());
        questionDTO.setDate(question.getDate());
        questionDTO.setViewCount(question.getViewCount());
        questionDTO.setVoteCount(question.getVoteCount());
        question.getTags().forEach(tag -> questionDTO.getTags().add(tag.getTitle()));

        question.incrementViewCount();
        questionRepository.save(question);

        List<Answer> answers = answerRepository.findByQuestion(question);

        if (answers.isEmpty()) {
            return new GetQuestionResponse(questionDTO, Collections.emptyList());
        }

        List<AnswerDTO> answerDTOs = answers.stream()
                .map(AnswerDTO::new)
                .collect(Collectors.toList());

        return new GetQuestionResponse(questionDTO, answerDTOs);
    }

    public Long createQuestion(CreateQuestionRequest request) {
        User user = userService.getUser(request.getUserId());
        List<Tag> tags = request.getTags().stream()
                .map(tagService::getOrCreateTag)
                .collect(Collectors.toList());

        Question question = new Question(request, tags, user);
        question = questionRepository.save(question);

        return question.getId();
    }

    public AnswerQuestionResponse answerQuestion(AnswerQuestionRequest request, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question Not Found with id:" + questionId));

        User user = userService.getUser(request.getUserId());

        Answer answer = new Answer();
        answer.setDescription(request.getAnswerText());
        answer.setDate(LocalDateTime.now());
        answer.setUser(user);
        answer.setQuestion(question);

        answer = answerRepository.save(answer);
        return new AnswerQuestionResponse(question.getId(), answer.getId());
    }

    public AnswerQuestionResponse updateAnswer(UpdateAnswerRequest request, Long questionId, Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer Not Found with id:" + answerId));

        if (!questionId.equals(answer.getQuestion().getId())) {
            throw new RuntimeException("Question id's do not match");
        }
        answer.setDescription(request.getAnswerText());

        answer = answerRepository.save(answer);
        return new AnswerQuestionResponse(questionId, answer.getId());
    }

    public VoteResponse voteQuestion(Vote vote, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question Not Found with id:" + questionId));

        question.vote(vote);
        question = questionRepository.save(question);
        return new VoteResponse(question.getVoteCount());
    }

    public VoteResponse voteAnswer(Vote vote, Long questionId, Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer Not Found with id:" + answerId));

        if (!questionId.equals(answer.getQuestion().getId())) {
            throw new RuntimeException("Question id's do not match");
        }

        answer.vote(vote);
        answer = answerRepository.save(answer);
        return new VoteResponse(answer.getVoteCount());
    }
}
