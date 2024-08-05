package net.skhu.likelion12thteam03be.SurveyColor;

import jakarta.persistence.*;
import lombok.*;
import net.skhu.likelion12thteam03be.color.domian.Color;
import net.skhu.likelion12thteam03be.survey.domain.Survey;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @Builder
    public SurveyColor(Survey survey, Color color) {
        this.survey = survey;
        this.color = color;
    }
}
