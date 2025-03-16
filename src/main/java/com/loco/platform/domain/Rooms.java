package com.loco.platform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rooms extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "room_id")
  private Long id;

  //    @ManyToOne(fetch = FetchType.LAZY)
  //    @JoinColumn(name = "user_id", nullable = false)
  //    private Users users;

  @Column(nullable = false)
  private String name;

  @Lob
  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "is_private")
  private boolean isPrivate = false;

  @Column(name = "share_link", unique = true)
  private String shareLink;
}
