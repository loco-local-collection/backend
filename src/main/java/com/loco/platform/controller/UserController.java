package com.loco.platform.controller;

import com.loco.platform.domain.Users;
import com.loco.platform.dto.UserInfoDto;
import com.loco.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private static final String SUCCESS_DELETE_USER_MESSAGE = "회원의 정보가 정상적으로 삭제되었습니다.";

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserInfoDto> getUserInfo(@AuthenticationPrincipal Users users) {
        UserInfoDto userInfo = userService.getUserInfo(users.getId());
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal Users users) {
        userService.deleteUserById(users.getId());
        return ResponseEntity.status(HttpStatus.OK).body(SUCCESS_DELETE_USER_MESSAGE);
    }
}
