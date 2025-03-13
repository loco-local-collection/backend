package com.loco.platform.service;

import com.loco.platform.domain.Users;
import com.loco.platform.dto.UserInfoDto;
import com.loco.platform.exception.ErrorCode;
import com.loco.platform.exception.UserException;
import com.loco.platform.repository.TokenRepository;
import com.loco.platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserInfoDto getUserInfo(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXIST_USER));

        return UserInfoDto.from(user);
    }

    public void deleteUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(ErrorCode.NOT_EXIST_USER));


        userRepository.deleteById(id);
    }
}
