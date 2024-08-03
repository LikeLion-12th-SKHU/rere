package net.skhu.likelion12thteam03be.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.color.domian.Color;
import net.skhu.likelion12thteam03be.emotion.domain.Emotion;
import net.skhu.likelion12thteam03be.user.domain.User;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @ManyToMany
    @JoinTable(
            name = "survey_colors",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private List<Color> colors;

    private int score;

    @Builder
    public Survey(int score, Emotion emotion, List<Color> colors, User user) {
        this.score = score;
        this.emotion = emotion;
        this.colors = colors;
        this.user = user;
    }

    public void update(int score, Emotion emotion, List<Color> colors) {
        this.score = score;
        this.emotion = emotion;
        this.colors = colors;
    }
}
