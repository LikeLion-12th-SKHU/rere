package net.skhu.likelion12thteam03be.color.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.SurveyColor.SurveyColor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "color")
    private List<SurveyColor> colors = new ArrayList<>();

    @Builder
    public Color(Long id, String name, String mood, String comment) {
        this.id = id;
        this.name = name;
        this.mood = mood;
        this.comment = comment;
    }
}
