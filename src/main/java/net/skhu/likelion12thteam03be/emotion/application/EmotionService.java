package net.skhu.likelion12thteam03be.emotion.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.emotion.domain.Emotion;
import net.skhu.likelion12thteam03be.emotion.domain.Emotions;
import net.skhu.likelion12thteam03be.emotion.domain.repository.EmotionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionService {
    private EmotionRepository emotionRepository;

/*    public void saveEmotions(){
        Emotion emotion = Emotion.builder()
                .name(Emotions.행복한)
                .furniture("모던 쇼파, 커피 테이블")
                .type("돌고래")
                .animalPic("~~~~~~~~~~~~~~~~~/post/1_dolphin.png")
                .build();
        emotionRepository.save(emotion);
    }*/
}
