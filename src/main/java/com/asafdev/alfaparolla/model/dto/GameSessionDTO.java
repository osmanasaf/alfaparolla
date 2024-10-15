package com.asafdev.alfaparolla.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class GameSessionDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, String> questionsAnswered;
    private int passed;

    public GameSessionDTO() {}

    public GameSessionDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Map<String, String> questionsAnswered, int passed) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionsAnswered = questionsAnswered;
        this.passed = passed;
    }
}
