/*
package net.skhu.likelion12thteam03be.SelectColor.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.color.domian.Color;
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
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Builder
    public SelectColor(Long id, Survey survey, Color Color) {
        this.id = id;
        this.survey = survey;
        this.color = color;
    }
}
*/
