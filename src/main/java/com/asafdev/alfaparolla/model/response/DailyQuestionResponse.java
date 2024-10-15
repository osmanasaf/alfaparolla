package com.asafdev.alfaparolla.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DailyQuestionResponse {

    private String questionText;
    private String answer;
    private String letter;
    private LocalDate date;

    public DailyQuestionResponse(String questionText, String answer, String letter, LocalDate date) {
        this.questionText = questionText;
        this.answer = answer;
        this.letter = letter;
        this.date = date;
    }
}
