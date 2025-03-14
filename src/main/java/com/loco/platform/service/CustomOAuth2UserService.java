package com.loco.platform.service;

import com.loco.platform.domain.Users;
import com.loco.platform.dto.OAuth2UserInfo;
import com.loco.platform.dto.PrincipalDetails;
import com.loco.platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes);

        Users user = getOrSave(oAuth2UserInfo);

        return new PrincipalDetails(user, oAuth2UserAttributes, userNameAttributeName);
    }

    private Users getOrSave(OAuth2UserInfo oAuth2UserInfo) {
        Users user = userRepository.findByProfileImage(oAuth2UserInfo.profile())
                .orElseGet(oAuth2UserInfo::toEntity);
        return userRepository.save(user);
    }
}
