package com.asafdev.alfaparolla.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class GameSessionResponse {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, String> questionsAnswered;
    private int passed;
    private String message;

    public GameSessionResponse() {}

    public GameSessionResponse(Long id, LocalDateTime startTime, LocalDateTime endTime, Map<String, String> questionsAnswered, int passed, String message) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionsAnswered = questionsAnswered;
        this.passed = passed;
        this.message = message;
    }
}
