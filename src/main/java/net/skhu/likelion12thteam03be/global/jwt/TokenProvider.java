package net.skhu.likelion12thteam03be.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.skhu.likelion12thteam03be.global.jwt.exception.CustomAuthenticationException;
import net.skhu.likelion12thteam03be.user.domain.User;
import net.skhu.likelion12thteam03be.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component  //Component
public class TokenProvider {
    private final UserRepository userRepository;

    @Value("${jwt.access-token-validity-in-milliseconds}")
    private String tokenExpireTime;

    @Value("${jwt.secret}")
    private String secret;
    private Key key;

/*    @PostConstruct
    public void init() {
        byte[] key = Decoders.BASE64URL.decode(secret);
        this.key = Keys.hmacShaKeyFor(key);
    }*/
    @PostConstruct
    public void init() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String loginId) {   // loginId
        Date date = new Date();
        Date expiryDate = new Date(date.getTime() + Long.parseLong(tokenExpireTime));

        return Jwts.builder()
                .setSubject(loginId)    // loginId로
                .setIssuedAt(date)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact(); // compact
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (UnsupportedJwtException | MalformedJwtException exception) {
            log.error("JWT가 유효하지 않습니다.");
            throw new CustomAuthenticationException("JWT가 유효하지 않습니다.");
        } catch (SignatureException exception) {
            log.error("JWT 서명 검증에 실패했습니다.");
            throw new CustomAuthenticationException("JWT 서명 검증에 실패했습니다.");
        } catch (ExpiredJwtException exception) {
            log.error("JWT가 만료되었습니다.");
            throw new CustomAuthenticationException("JWT가 만료되었습니다.");
        } catch (IllegalArgumentException exception) {
            log.error("JWT가 null이거나 비어 있거나 공백만 있습니다.");
            throw new CustomAuthenticationException("JWT가 null이거나 비어 있거나 공백만 있습니다.");
        } catch (Exception exception) {
            log.error("JWT 검증에 실패했습니다.", exception);
            throw new CustomAuthenticationException("JWT 검증에 실패했습니다.");
        }

    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        User user = userRepository.findByLoginId(claims.getSubject()).orElseThrow();

        return new UsernamePasswordAuthenticationToken(user.getLoginId(), "");
    }

}
