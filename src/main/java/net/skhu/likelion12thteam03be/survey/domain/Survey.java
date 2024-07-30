package net.skhu.likelion12thteam03be.survey.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.SelectColor.domain.SelectColor;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emotion_id")    // 외래키 join
    Emotion emotion;

    @OneToMany(mappedBy = "survey") // color랑 manytomany 중간테이블 생성
    private List<SelectColor> selectColorList;

    private int score;

    @Builder
    public Survey(Long id, User user, Emotion emotion, List<SelectColor> selectColorList, int score) {
        this.id = id;
        this.user = user;
        this.emotion = emotion;
        this.selectColorList = selectColorList;
        this.score = score;
    }
}
