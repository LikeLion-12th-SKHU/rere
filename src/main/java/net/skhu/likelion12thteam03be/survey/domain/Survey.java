package net.skhu.likelion12thteam03be.survey.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.SurveyColor.SurveyColor;
import net.skhu.likelion12thteam03be.emotion.domain.Emotion;
import net.skhu.likelion12thteam03be.user.domain.User;

import java.util.ArrayList;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyColor> colors = new ArrayList<>();

    private int score;

    @Builder
    public Survey(int score, Emotion emotion, List<SurveyColor> colors, User user) {
        this.score = score;
        this.emotion = emotion;
        this.colors = colors;
        this.user = user;
    }

    public void update(int score, Emotion emotion, List<SurveyColor> colors) {
        this.score = score;
        this.emotion = emotion;
        this.colors.clear();
        this.colors.addAll(colors);
    }
}
