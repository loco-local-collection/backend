package com.loco.platform.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(nullable = false, unique = true)
    private String memberKey;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Rooms> rooms = new ArrayList<>();

    @Builder
    public Users(String nickname, String email, String profileImage, String memberKey, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.memberKey = memberKey;
        this.role = role;
    }
}
