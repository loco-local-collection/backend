package com.loco.platform.dto;

import com.loco.platform.domain.Users;

public record UserInfoDto(
        String email,
        String name,
        String profileImage) {

    public static UserInfoDto from(Users user) {
        return new UserInfoDto(user.getEmail(), user.getNickname(), user.getProfileImage());
    }
}
