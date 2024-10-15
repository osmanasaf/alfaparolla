package com.asafdev.alfaparolla.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "daily_questions")
@Setter
@Getter
public class DailyQuestion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private LocalDate date;

    public DailyQuestion() {}

    public DailyQuestion(Question question, LocalDate date) {
        this.question = question;
        this.date = date;
    }

}
