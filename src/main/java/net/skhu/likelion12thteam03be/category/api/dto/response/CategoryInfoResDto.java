package net.skhu.likelion12thteam03be.category.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.category.domain.Category;

@Builder
public record CategoryInfoResDto(
        Long categoryId,
        String name
) {
    public static CategoryInfoResDto from(Category category) {
        return CategoryInfoResDto.builder()
                .name(category.getName())
                .build();
    }
}
