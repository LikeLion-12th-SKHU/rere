package net.skhu.likelion12thteam03be.post.api.dto.request;

public record PostSaveReqDto(
        String title,
        String content,
        Long locationId,
        Integer time,
        Integer price,
        Long categoryId,
        Long moodId
) {
}
