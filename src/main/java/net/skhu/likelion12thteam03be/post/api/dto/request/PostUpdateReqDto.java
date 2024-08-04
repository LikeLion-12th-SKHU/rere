package net.skhu.likelion12thteam03be.post.api.dto.request;

import java.util.List;

public record PostUpdateReqDto(
        String title,
        String content,
        Long locationId,
        Integer time,
        Integer price,
        Long categoryId,
        List<Long> moodIds
) {
}
