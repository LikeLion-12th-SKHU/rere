package net.skhu.likelion12thteam03be.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.location.domain.Location;
import net.skhu.likelion12thteam03be.mood.domain.Mood;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostUpdateReqDto;
import net.skhu.likelion12thteam03be.user.domain.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long postId;

    private String title; // 제목
    private String content; // 내용

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location; // 거래 장소

    private Integer time; // 거래 시간
    private Integer price; // 가격

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "moodId")
    private Mood mood; // 감정 키워드

    private String imgUrl; // 사진

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String title, String content, Location location, Integer time, Integer price, Category category, Mood mood, String imgUrl, User user) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.time = time;
        this.price = price;
        this.category = category;
        this.mood = mood;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    public void update(Location location, Category category, PostUpdateReqDto postUpdateReqDto, Mood mood, String imgUrl) {
        this.title = postUpdateReqDto.title();
        this.content = postUpdateReqDto.content();
        this.location = location;
        this.time = postUpdateReqDto.time();
        this.price = postUpdateReqDto.price();
        this.category = category;
        this.mood = mood;
        this.imgUrl = imgUrl;
    }
}
