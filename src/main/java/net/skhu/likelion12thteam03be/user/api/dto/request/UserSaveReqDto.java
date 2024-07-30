package net.skhu.likelion12thteam03be.user.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserSaveReqDto(
        @NotBlank(message = "아이디를 입력해주세요")
        String loginId,
        @NotBlank(message = "비밀번호를 입력해주세요")
        String password,
        @NotBlank(message = "닉네임을 입력해주세요")
        String nickname
) {

}
