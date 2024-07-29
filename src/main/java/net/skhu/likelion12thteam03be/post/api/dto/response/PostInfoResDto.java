package net.skhu.likelion12thteam03be.post.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.post.domain.Post;

@Builder
public record PostInfoResDto(
        String title,
        String content,
        String imgUrl,
        String locationId,
        Integer time,
        Integer price,
        Category category,
        String emotionId,
        String colorId
) {
    public static PostInfoResDto from(Post post) {
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imgUrl(post.getImgUrl())
                .locationId(post.getLocationId())
                .time(post.getTime())
                .price(post.getPrice())
                .category(post.getCategory())
                .emotionId(post.getEmotionId())
                .colorId(post.getColorId())
                .build();
    }
}
