package com.loco.platform.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;
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
    private String email;
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;
    private String provider;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rooms> rooms = new ArrayList<>();

    @Builder
    public Users(Long id, String email, String password, String nickname, String profileImage,
        String provider, String socialId, boolean isDeleted, List<Rooms> rooms, Timestamp createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.provider = provider;
        this.socialId = socialId;
        this.isDeleted = isDeleted;
        this.rooms = rooms;
        super.createdAt = createdAt;
    }
}
