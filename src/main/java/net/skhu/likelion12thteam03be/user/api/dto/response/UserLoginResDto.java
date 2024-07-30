package net.skhu.likelion12thteam03be.user.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.user.domain.User;

@Builder
public record UserLoginResDto(
        String loginId,
        String nickname,
        String token
) {
    public static UserLoginResDto of(User user, String token) {
        return UserLoginResDto.builder()
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .token(token)
                .build();
    }
}
