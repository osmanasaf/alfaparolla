package com.example.yourproject.service;

import com.asafdev.alfaparolla.mapper.DailyQuestionMapper;
import com.asafdev.alfaparolla.model.DailyQuestion;
import com.asafdev.alfaparolla.model.Question;
import com.asafdev.alfaparolla.model.dto.DailyQuestionDTO;
import com.asafdev.alfaparolla.model.response.DailyQuestionResponse;
import com.asafdev.alfaparolla.repository.DailyQuestionRepository;
import com.asafdev.alfaparolla.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Service
public class DailyQuestionService {

    @Autowired
    private DailyQuestionRepository dailyQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @CacheEvict(value = "dailyQuestions", allEntries = true)
    public ResponseEntity<?> generateDailyAlphabetQuestions() {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random random = new Random();
        LocalDate today = LocalDate.now();

        for (char letter : alphabet) {
            List<Question> questionsForLetter = questionRepository.findAllByLetter(String.valueOf(letter));
            if(questionsForLetter.isEmpty()){
                return new ResponseEntity<>("There is no question for this letter", HttpStatus.BAD_REQUEST);
            }
            Question selectedQuestion = questionsForLetter.get(random.nextInt(questionsForLetter.size()));

            DailyQuestion dailyQuestion = new DailyQuestion();
            dailyQuestion.setQuestion(selectedQuestion);
            dailyQuestion.setDate(today);
            dailyQuestionRepository.save(dailyQuestion);

            selectedQuestion.setAsked(true);
            questionRepository.save(selectedQuestion);
        }

        return new ResponseEntity<>("Daily alphabet questions created successfully", HttpStatus.CREATED);
    }

    @Cacheable(value = "dailyQuestions", key = "#date")
    public ResponseEntity<?> getQuestionsForDate(LocalDate date) {
        List<DailyQuestion> dailyQuestions = dailyQuestionRepository.findByDate(date);
        if (dailyQuestions.isEmpty()) {
            return new ResponseEntity<>("No daily questions found for this date", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(DailyQuestionMapper.toResponseAll(dailyQuestions), HttpStatus.OK);
    }

    @CacheEvict(value = "dailyQuestions", allEntries = true)
    public ResponseEntity<?> addDailyQuestion(DailyQuestionDTO dailyQuestionDTO) {
        Question question = questionRepository.findById(dailyQuestionDTO.getQuestionId()).orElse(null);
        if (question == null) {
            return new ResponseEntity<>("Question not found", HttpStatus.BAD_REQUEST);
        }

        DailyQuestion dailyQuestion = new DailyQuestion();
        dailyQuestion.setDate(LocalDate.parse(dailyQuestionDTO.getDate()));
        dailyQuestion.setQuestion(question);
        dailyQuestionRepository.save(dailyQuestion);

        return new ResponseEntity<>(DailyQuestionMapper.toResponse(dailyQuestion), HttpStatus.CREATED);
    }

}
