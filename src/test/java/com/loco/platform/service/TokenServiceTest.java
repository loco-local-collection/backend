package com.loco.platform.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.loco.platform.domain.Token;
import com.loco.platform.exception.TokenException;
import com.loco.platform.repository.TokenRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
public class TokenServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenService tokenService;

    @Spy
    private Token token;

    @BeforeEach
    public void init() {
        String memberKey = "memberKey";
        String refreshToken = "refreshToken";
        String accessToken = "accessToken";

        Token sampleToken = new Token(memberKey, refreshToken, accessToken);
        token = spy(sampleToken);
    }

    @Test
    @DisplayName("새로운 토큰을 저장할 때 액세스 토큰이 없는 경우")
    public void 새로운_토큰을_저장할_때_액세스_토큰이_없는_경우() {
        //given
        String memberKey = "memberKey";
        String refreshToken = "refreshToken";
        String accessToken = "accessToken";

        when(tokenRepository.findByAccessToken(accessToken)).thenReturn(Optional.empty());

        //when
        tokenService.saveOrUpdate(memberKey, refreshToken, accessToken);

        //then
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    @DisplayName("기존 토큰을 업데이트 할 때 액세스 토큰이 일치하는 경우")
    public void 기존_토큰을_업데이트_할_때_액세스_토큰이_일치하는_경우() {
        //given
        String memberKey = "memberKey";
        String refreshToken = "refreshToken";
        String accessToken = "accessToken";

        when(tokenRepository.findByAccessToken(accessToken)).thenReturn(Optional.of(token));

        //when
        tokenService.saveOrUpdate(memberKey, refreshToken, accessToken);

        //then
        verify(tokenRepository, times(1)).save(any(Token.class));
        verify(token, times(1)).updateRefreshToken(refreshToken);
    }

    @Test
    @DisplayName("새로운 토큰을 저장할 때 액세스 토큰이 null인 경우")
    public void 새로운_토큰을_저장할_때_액세스_토큰이_null인_경우() {
        //given
        String accessToken = null;

        //when
        when(tokenRepository.findByAccessToken(accessToken)).thenReturn(Optional.empty());

        //then
        assertThrows(TokenException.class,
                () -> {
                    tokenService.findByAccessTokenOrThrow(accessToken);
                });
    }

    @Test
    @DisplayName("토큰의 Access Token을 업데이트 할 수 있다")
    public void 토큰을_업데이트_할_수_있다() throws Exception {
        //given
        String accessToken = "newAccessToken";

        //when
        tokenService.updateToken(accessToken, token);

        //then
        verify(token, times(1)).updateAccessToken(accessToken);
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

}
