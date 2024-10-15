package com.asafdev.alfaparolla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question extends BaseEntity {

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false, length = 1)
    private String letter;

    private String category;

    @Column(nullable = false)
    private boolean asked;


    public Question() {}

    public Question(String questionText, String answer, String letter, String category, boolean asked) {
        this.questionText = questionText;
        this.answer = answer;
        this.letter = letter;
        this.category = category;
        this.asked = asked;
    }

}
