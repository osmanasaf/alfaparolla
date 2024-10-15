package com.asafdev.alfaparolla.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "game_sessions")
@Setter
@Getter
public class GameSession extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ElementCollection
    @CollectionTable(name = "answered_questions", joinColumns = @JoinColumn(name = "session_id"))
    @MapKeyColumn(name = "letter")
    @Column(name = "answer")
    private Map<String, String> questionsAnswered;
    private int passed;

    public GameSession() {}

    public GameSession(LocalDateTime startTime, LocalDateTime endTime, Map<String, String> questionsAnswered, int passed) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionsAnswered = questionsAnswered;
        this.passed = passed;
    }

}
