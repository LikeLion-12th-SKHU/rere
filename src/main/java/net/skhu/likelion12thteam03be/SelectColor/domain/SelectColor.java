package net.skhu.likelion12thteam03be.SelectColor.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.survey.domain.Survey;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selectColor_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    Survey survey;

    @ManyToOne
    @JoinColumn(name = "color_id")
    SelectColor selectColor;

    @Builder
    public SelectColor(Long id, Survey survey, SelectColor selectColor) {
        this.id = id;
        this.survey = survey;
        this.selectColor = selectColor;
    }
}
