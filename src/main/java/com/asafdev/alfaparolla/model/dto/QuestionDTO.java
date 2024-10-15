package com.asafdev.alfaparolla.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

    private Long id;
    private String questionText;
    private String letter;
    private String answer;
    private String category;
    private boolean asked;

    public QuestionDTO() {}

    public QuestionDTO(Long id, String questionText, String letter,String answer, boolean asked) {
        this.id = id;
        this.questionText = questionText;
        this.letter = letter;
        this.answer = answer;
        this.asked = asked;
    }
}
