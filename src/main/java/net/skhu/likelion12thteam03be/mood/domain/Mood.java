package net.skhu.likelion12thteam03be.mood.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.post.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moodId")
    private Long moodId;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "moods", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Mood(String name) {
        this.name = name;
    }
}
