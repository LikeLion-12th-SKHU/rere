package net.skhu.likelion12thteam03be.post.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.location.domain.Location;
import net.skhu.likelion12thteam03be.mood.domain.Mood;
import net.skhu.likelion12thteam03be.post.domain.Post;

import java.time.LocalDateTime;

@Builder
public record PostInfoResDto(
        String title,
        String content,
        String imgUrl,
        Location location,
        Integer time,
        Integer price,
        Category category,
        Mood mood,
        LocalDateTime createDate,
        LocalDateTime modifiedDate
) {
    public static PostInfoResDto from(Post post) {
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .location(post.getLocation())
                .time(post.getTime())
                .price(post.getPrice())
                .category(post.getCategory())
                .mood(post.getMood())
                .imgUrl(post.getImgUrl())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
