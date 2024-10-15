package com.asafdev.alfaparolla.mapper;

import com.asafdev.alfaparolla.model.Question;
import com.asafdev.alfaparolla.model.dto.QuestionDTO;
import com.asafdev.alfaparolla.model.response.QuestionResponse;

public class QuestionMapper {

    public static QuestionDTO toDTO(Question question) {
        return new QuestionDTO(
            question.getId(),
            question.getQuestionText(),
            question.getLetter(),
            question.getAnswer(),
            question.isAsked()
        );
    }

    public static QuestionResponse toResponse(Question question) {
        return new QuestionResponse(
            question.getId(),
            question.getQuestionText(),
            question.getLetter(),
            question.getAnswer()
        );
    }

    public static Question toEntity(QuestionDTO questionDTO){
        return new Question(
                questionDTO.getQuestionText(),
                questionDTO.getAnswer(),
                questionDTO.getLetter(),
                questionDTO.getCategory(),
               false
        );
    }
}
