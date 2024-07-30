package net.skhu.likelion12thteam03be.mood.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.mood.domain.Mood;

@Builder
public record MoodInfoResDto(
        Long moodId,
        String name
) {
    public static MoodInfoResDto from(Mood mood) {
        return MoodInfoResDto.builder()
                .name(mood.getName())
                .build();
    }
}
