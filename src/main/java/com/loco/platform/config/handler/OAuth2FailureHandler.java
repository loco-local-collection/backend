package com.loco.platform.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2FailureHandler implements AuthenticationFailureHandler {

    private static final String OAUTH_LOGIN_FAILURE_ERROR_MESSAGE = "소셜 로그인에 실패했습니다.";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, OAUTH_LOGIN_FAILURE_ERROR_MESSAGE);
    }
}
