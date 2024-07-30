package net.skhu.likelion12thteam03be.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.skhu.likelion12thteam03be.survey.domain.Survey;
import net.skhu.likelion12thteam03be.user.exception.InvalidNickNameAddressException;
import net.skhu.likelion12thteam03be.user.exception.InvalidUserException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[a-z A-Z0-9가-힣]*$");
    private static final int MAX_NICKNAME_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> surveys;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();*/

    @Builder
    public User(String loginId, String password, String nickname, Role role, List<Survey> surveys) {
        validateNickname(nickname);
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.surveys = surveys;
    }

    private void validateNickname(String nickname) {
        Matcher matcher = NICKNAME_PATTERN.matcher(nickname);
        if (!matcher.matches()) {
            throw new InvalidNickNameAddressException();
        }
        if (nickname.isEmpty() || nickname.length() >= MAX_NICKNAME_LENGTH) {
            throw new InvalidUserException(String.format("닉네임은 1자 이상 %d자 이하여야 합니다.", MAX_NICKNAME_LENGTH));
        }
    }
}
