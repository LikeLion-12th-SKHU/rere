package net.skhu.likelion12thteam03be.user.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginReqDto(
        @NotBlank
        String loginId,
        @NotBlank
        String password
) {
}
