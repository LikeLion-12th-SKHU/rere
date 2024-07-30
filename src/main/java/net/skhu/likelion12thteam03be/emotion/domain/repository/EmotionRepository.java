package net.skhu.likelion12thteam03be.emotion.domain.repository;

import net.skhu.likelion12thteam03be.emotion.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
