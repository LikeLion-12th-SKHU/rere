package net.skhu.likelion12thteam03be.survey.domain.repository;

import net.skhu.likelion12thteam03be.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    boolean existsByUserLoginId(String loginId);

    Survey findByUserLoginId(String loginId);
}
