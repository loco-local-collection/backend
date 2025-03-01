package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String email;
    private String password;

    @NotNull
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;
    private String provider;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Rooms> rooms = new ArrayList<>();
}
