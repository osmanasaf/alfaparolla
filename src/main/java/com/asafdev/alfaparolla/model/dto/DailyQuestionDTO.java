package com.asafdev.alfaparolla.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyQuestionDTO {

    private Long id;
    private Long questionId;
    private String date;

    public DailyQuestionDTO() {}

    public DailyQuestionDTO(Long id, Long questionId,  String date) {
        this.id = id;
        this.questionId = questionId;
        this.date = date;
    }
}
