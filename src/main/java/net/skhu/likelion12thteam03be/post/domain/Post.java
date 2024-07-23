package net.skhu.likelion12thteam03be.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostUpdateReqDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title; // 제목
    private String content; // 내용
    private String imgUrl; // 사진
    private String locationId; // 거래 장소
    private Integer time; // 거래 시간
    private Integer price; // 가격

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String emotionId; // 감정 키워드
    private String colorId; // 색상 코드

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;*/

    @Builder
    public Post(String title, String content, String imgUrl, String locationId, Integer time, Integer price, Category category, String emotionId, String colorId) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.locationId = locationId;
        this.time = time;
        this.price = price;
        this.category = category;
        this.emotionId = emotionId;
        this.colorId = colorId;
    }

    public void update(Category category, PostUpdateReqDto postUpdateReqDto) {
        this.title = postUpdateReqDto.title();
        this.content = postUpdateReqDto.content();
        this.imgUrl = postUpdateReqDto.imgUrl();
        this.locationId = postUpdateReqDto.locationId();
        this.time = postUpdateReqDto.time();
        this.price = postUpdateReqDto.price();
        this.category = category;
        this.emotionId = postUpdateReqDto.emotionId();
        this.colorId = postUpdateReqDto.colorId();
    }
}
