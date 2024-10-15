package com.example.yourproject.service;

import com.asafdev.alfaparolla.mapper.QuestionMapper;
import com.asafdev.alfaparolla.model.Question;
import com.asafdev.alfaparolla.model.dto.QuestionDTO;
import com.asafdev.alfaparolla.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            return new ResponseEntity<>("Question not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(QuestionMapper.toResponse(question), HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestionsByLetter(String letter) {
        List<Question> questions = questionRepository.findAllByLetter(letter);
        if (questions.isEmpty()) {
            return new ResponseEntity<>("No questions found for this letter", HttpStatus.BAD_REQUEST);
        }
        List<QuestionDTO> questionDTOs = questions.stream()
                .map(QuestionMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
    }

    public ResponseEntity<?> addQuestion(QuestionDTO questionDTO) {
        Question question = QuestionMapper.toEntity(questionDTO);
        question.setAsked(false);
        questionRepository.save(question);
        return new ResponseEntity<>(QuestionMapper.toDTO(question), HttpStatus.CREATED);
    }

    public ResponseEntity<?> addQuestions(List<QuestionDTO> questionDTOList) {
        List<Question> questions = questionDTOList.stream()
                .map(QuestionMapper::toEntity)
                .collect(Collectors.toList());
        questionRepository.saveAll(questions);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateQuestion(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            return new ResponseEntity<>("Question not found", HttpStatus.BAD_REQUEST);
        }

        question.setQuestionText(questionDTO.getQuestionText());
        question.setLetter(questionDTO.getLetter());
        question.setAsked(questionDTO.isAsked());
        questionRepository.save(question);
        return new ResponseEntity<>(QuestionMapper.toDTO(question), HttpStatus.OK);
    }
}
