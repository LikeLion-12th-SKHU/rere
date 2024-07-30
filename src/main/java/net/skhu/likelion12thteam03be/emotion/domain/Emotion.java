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
    private Long id;

    private String name;
    private String furniture;
    private String type;
    private String animalPic;

    @Builder
    public Emotion(Long id, String name, String furniture, String type, String animalPic) {
        this.id = id;
        this.name = name;
        this.furniture = furniture;
        this.type = type;
        this.animalPic = animalPic;
    }
}
