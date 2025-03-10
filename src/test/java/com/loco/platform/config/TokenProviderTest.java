package com.loco.platform.config;

import static com.loco.platform.exception.ErrorCode.TOKEN_EXPIRED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.loco.platform.config.jwt.TokenProvider;
import com.loco.platform.domain.Role;
import com.loco.platform.exception.TokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Value("${custom.jwt.key}")
    private String key;
    private SecretKey secretKey;

    @BeforeAll
    public void setSecretKey() {
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    @Test
    @DisplayName("Authentication을 사용해 Access Token을 생성한다")
    public void AccessToken_생성() throws Exception {
        //given
        Authentication authentication = new UsernamePasswordAuthenticationToken("username",
                "password");

        //when
        String accessToken = tokenProvider.generateAccessToken(authentication);

        //then
        assertNotNull(accessToken);
    }

    @Test
    @DisplayName("만료된 Access Token은 오류를 발생시킨다")
    public void 만료_AccessToken_오류() throws Exception {
        //given
        Authentication authentication = new UsernamePasswordAuthenticationToken("username",
                "password");
        String authorities = Role.USER.name();
        Date expiredDate = new Date(System.currentTimeMillis() - 1000 * 60 * 60);
        String expiredToken = Jwts.builder()
                .subject(authentication.getName())
                .claim("role", authorities)
                .issuedAt(new Date())
                .expiration(expiredDate)
                .signWith(secretKey, SIG.HS512)
                .compact();

        //when

        //then
        TokenException tokenException = assertThrows(TokenException.class, () -> {
            tokenProvider.validateToken(expiredToken);
        });

        assertThat(TOKEN_EXPIRED.getMessage()).isEqualTo(tokenException.getMessage());
    }

    @Test
    @DisplayName("Access Toekn으로부터 Refresh Token을 갱신한다")
    public void RefreshToken_갱신() throws Exception {
        //given
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken("username",
                "password", authorities);

        String expiredAccessToken = tokenProvider.generateAccessToken(authentication);
        tokenProvider.generateRefreshToken(authentication, expiredAccessToken);

        //when
        String newAccessToken = tokenProvider.reissueAccessToken(expiredAccessToken);

        //then
        assertNotNull(newAccessToken);
        assertNotEquals(expiredAccessToken, newAccessToken);
    }


}
