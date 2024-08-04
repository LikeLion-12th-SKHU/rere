package net.skhu.likelion12thteam03be.color.domian;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;

    private String name;
    private String mood;
    private String comment;

    @Builder
    public Color(Long id, String name, String mood, String comment) {
        this.id = id;
        this.name = name;
        this.mood = mood;
        this.comment = comment;
    }
}