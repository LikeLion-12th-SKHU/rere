package net.skhu.likelion12thteam03be.emotion.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Emotions name;

    private String furniture;
    private String type;
    private String animalPic;

    @Builder
    public Emotion(Emotions name, String furniture, String type, String animalPic) {
        this.name = name;
        this.furniture = furniture;
        this.type = type;
        this.animalPic = animalPic;
    }
}
