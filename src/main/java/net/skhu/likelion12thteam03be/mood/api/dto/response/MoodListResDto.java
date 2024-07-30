package net.skhu.likelion12thteam03be.mood.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MoodListResDto(
        List<MoodInfoResDto> moodList
) {
    public static MoodListResDto from(List<MoodInfoResDto> moodList) {
        return MoodListResDto.builder()
                .moodList(moodList)
                .build();
    }
}
