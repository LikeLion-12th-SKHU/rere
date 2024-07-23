package net.skhu.likelion12thteam03be.user.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.global.jwt.TokenProvider;
import net.skhu.likelion12thteam03be.user.api.dto.request.UserLoginReqDto;
import net.skhu.likelion12thteam03be.user.api.dto.request.UserSaveReqDto;
import net.skhu.likelion12thteam03be.user.api.dto.response.UserLoginResDto;
import net.skhu.likelion12thteam03be.user.domain.Role;
import net.skhu.likelion12thteam03be.user.domain.User;
import net.skhu.likelion12thteam03be.user.domain.repository.UserRepository;
import net.skhu.likelion12thteam03be.user.exception.InvalidUserException;
import net.skhu.likelion12thteam03be.user.exception.NotFoundUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 회원가입
    @Transactional
    public void join(UserSaveReqDto userSaveReqDto) {
        if (userRepository.existsByLoginId(userSaveReqDto.loginId())) {
            throw new InvalidUserException("이미 존재하는 아이디입니다.");  // incaalidUserException
        }
        User user = User.builder()
                .loginId(userSaveReqDto.loginId())
                .password(passwordEncoder.encode(userSaveReqDto.password()))
                .nickname(userSaveReqDto.nickname())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
    }

    // 로그인(토큰 발급)
    public UserLoginResDto login(UserLoginReqDto userLoginReqDto) {
        User user = userRepository.findByLoginId(userLoginReqDto.loginId())
                .orElseThrow(NotFoundUserException::new);
        String token = tokenProvider.generateToken(user.getLoginId());
        if (!passwordEncoder.matches(userLoginReqDto.password(), user.getPassword())) {
            throw new InvalidUserException("패스워드가 일치하지 않습니다.");
        }
        return UserLoginResDto.of(user, token);
    }
}
