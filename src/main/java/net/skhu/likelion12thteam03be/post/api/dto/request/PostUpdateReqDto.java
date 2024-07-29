package net.skhu.likelion12thteam03be.post.api.dto.request;

public record PostUpdateReqDto(
        String title,
        String content,
        String imgUrl,
        String locationId,
        Integer time,
        Integer price,
        Long categoryId,
        String emotionId,
        String colorId
) {
}
