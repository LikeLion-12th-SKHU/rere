package net.skhu.likelion12thteam03be.post.api.dto.request;

public record PostSaveReqDto(
        String title,
        String content,
        String imgUrl,
        Long locationId,
        Integer time,
        Integer price,
        Long categoryId,
        Long emotionId,
        Long colorId
) {
}
