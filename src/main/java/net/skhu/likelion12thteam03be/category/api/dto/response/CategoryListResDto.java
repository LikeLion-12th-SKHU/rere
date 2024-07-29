package net.skhu.likelion12thteam03be.category.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryListResDto(
        List<CategoryInfoResDto> categoryList
) {
    public static CategoryListResDto from(List<CategoryInfoResDto> categoryList) {
        return CategoryListResDto.builder()
                .categoryList(categoryList)
                .build();
    }
}
