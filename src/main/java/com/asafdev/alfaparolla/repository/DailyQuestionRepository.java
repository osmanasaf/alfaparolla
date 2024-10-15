package com.asafdev.alfaparolla.repository;

import com.asafdev.alfaparolla.model.DailyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyQuestionRepository extends JpaRepository<DailyQuestion, Long> {
    List<DailyQuestion> findByDate(LocalDate date);
}
