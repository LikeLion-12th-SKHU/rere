package net.skhu.likelion12thteam03be.post.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.location.domain.Location;
import net.skhu.likelion12thteam03be.post.domain.Post;

@Builder
public record PostInfoResDto(
        String title,
        String content,
        String imgUrl,
        Location location,
        Integer time,
        Integer price,
        Category category,
        Long emotionId,
        Long colorId
) {
    public static PostInfoResDto from(Post post) {
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imgUrl(post.getImgUrl())
                .location(post.getLocation())
                .time(post.getTime())
                .price(post.getPrice())
                .category(post.getCategory())
                .emotionId(post.getEmotionId())
                .colorId(post.getColorId())
                .build();
    }
}
