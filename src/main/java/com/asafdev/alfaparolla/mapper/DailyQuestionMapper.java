package com.asafdev.alfaparolla.mapper;


import com.asafdev.alfaparolla.model.DailyQuestion;
import com.asafdev.alfaparolla.model.response.DailyQuestionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DailyQuestionMapper {

    public static DailyQuestionResponse toResponse(DailyQuestion dailyQuestion) {
        return new DailyQuestionResponse(
            dailyQuestion.getQuestion().getQuestionText(),
            dailyQuestion.getQuestion().getAnswer(),
            dailyQuestion.getQuestion().getLetter(),
            dailyQuestion.getDate()
        );
    }

    public static List<DailyQuestionResponse> toResponseAll(List<DailyQuestion> dailyQuestions) {
        return dailyQuestions.stream()
                .map(DailyQuestionMapper::toResponse)
                .collect(Collectors.toList());
    }
}
