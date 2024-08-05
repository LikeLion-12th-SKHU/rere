package net.skhu.likelion12thteam03be.survey.domain.repository;

import net.skhu.likelion12thteam03be.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    boolean existsByUserLoginId(String loginId);

    Optional<Survey> findByUserLoginId(String loginId);
}
