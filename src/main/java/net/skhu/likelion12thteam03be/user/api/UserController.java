package net.skhu.likelion12thteam03be.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.global.template.RspTemplate;
import net.skhu.likelion12thteam03be.user.api.dto.request.UserLoginReqDto;
import net.skhu.likelion12thteam03be.user.api.dto.request.UserSaveReqDto;
import net.skhu.likelion12thteam03be.user.api.dto.response.UserLoginResDto;
import net.skhu.likelion12thteam03be.user.application.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// 배포 테스트
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "회원가입 및 로그인")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "회원가입 할 때 사용하는 API")
    public RspTemplate<String> join(@RequestBody @Valid UserSaveReqDto userSaveReqDto) {
        userService.join(userSaveReqDto);
        return new RspTemplate<>(HttpStatus.CREATED, "회원가입");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 시 사용하는 API")
    public RspTemplate<UserLoginResDto> login(@RequestBody @Valid UserLoginReqDto userLoginReqDto) {
        UserLoginResDto userLoginResDto = userService.login(userLoginReqDto);
        return new RspTemplate<>(HttpStatus.OK, "로그인", userLoginResDto);
    }
}
