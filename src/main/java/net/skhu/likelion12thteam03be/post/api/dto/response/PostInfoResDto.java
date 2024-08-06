package net.skhu.likelion12thteam03be.post.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.location.domain.Location;
import net.skhu.likelion12thteam03be.mood.domain.Mood;
import net.skhu.likelion12thteam03be.post.domain.Post;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostInfoResDto(
        Long postId,
        String nickname,
        String title,
        String content,
        String imgUrl,
        Location location,
        Integer time,
        Integer price,
        Category category,
        List<Mood> moods,
        LocalDateTime createDate,
        LocalDateTime modifiedDate
) {
    public static PostInfoResDto from(Post post) {
        return PostInfoResDto.builder()
                .postId(post.getPostId())
                .nickname(post.getUser().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .location(post.getLocation())
                .time(post.getTime())
                .price(post.getPrice())
                .category(post.getCategory())
                .moods(post.getMoods())
                .imgUrl(post.getImgUrl())
                .createDate(post.getCreateDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
