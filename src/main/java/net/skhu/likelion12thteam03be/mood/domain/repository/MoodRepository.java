package net.skhu.likelion12thteam03be.mood.domain.repository;

import net.skhu.likelion12thteam03be.mood.domain.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood, Long> {
}
