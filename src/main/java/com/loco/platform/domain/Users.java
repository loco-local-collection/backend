package com.loco.platform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
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

  //    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
  //    private List<Rooms> rooms = new ArrayList<>();
}
