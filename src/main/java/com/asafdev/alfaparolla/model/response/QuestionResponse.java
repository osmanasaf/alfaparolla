package com.asafdev.alfaparolla.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {

    private Long id;
    private String questionText;
    private String letter;
    private String answer;
    private boolean asked;

    public QuestionResponse() {}

    public QuestionResponse(Long id, String questionText, String letter, String answer) {
        this.id = id;
        this.questionText = questionText;
        this.letter = letter;
        this.answer = answer;
    }
}
