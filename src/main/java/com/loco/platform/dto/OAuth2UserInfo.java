package com.loco.platform.dto;

import static com.loco.platform.exception.ErrorCode.ILLEGAL_REGISTRATION_ID;

import com.loco.platform.config.utils.KeyGenerator;
import com.loco.platform.domain.Role;
import com.loco.platform.domain.Users;
import com.loco.platform.exception.AuthException;
import java.util.Map;
import lombok.Builder;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String profile
) {

    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "google" -> ofGoogle(attributes);
            case "naver" -> ofNaver(attributes);
            default -> throw new AuthException(ILLEGAL_REGISTRATION_ID);
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("picture"))
                .build();
    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuth2UserInfo.builder()
                .name((String) response.get("nickname"))
                .email((String) response.get("email"))
                .profile((String) response.get("profile_image"))
                .build();
    }

    public Users toEntity() {
        return Users.builder()
                .nickname(name)
                .email(email)
                .profileImage(profile)
                .memberKey(KeyGenerator.generateKey())
                .role(Role.USER)
                .build();
    }
}
