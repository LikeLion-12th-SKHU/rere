package net.skhu.likelion12thteam03be.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.global.template.RspTemplate;
import net.skhu.likelion12thteam03be.user.api.dto.request.UserLoginReqDto;
import net.skhu.likelion12thteam03be.user.api.dto.request.UserSaveReqDto;
import net.skhu.likelion12thteam03be.user.api.dto.response.UserLoginResDto;
import net.skhu.likelion12thteam03be.user.application.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public RspTemplate<String> join(@RequestBody @Valid UserSaveReqDto userSaveReqDto) {
        userService.join(userSaveReqDto);
        return new RspTemplate<>(HttpStatus.CREATED, "회원가입");
    }

    @PostMapping("/login")
    public RspTemplate<UserLoginResDto> login(@RequestBody @Valid UserLoginReqDto userLoginReqDto) {
        UserLoginResDto userLoginResDto = userService.login(userLoginReqDto);
        return new RspTemplate<>(HttpStatus.OK, "로그인", userLoginResDto);
    }
}
