package com.loco.platform.domain;

import com.loco.platform.dto.request.SaveRoomDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_private")
    private boolean isPrivate = false;

    @Column(name = "share_link", unique = true)
    private String shareLink;

    @Builder
    public Rooms(Long id, Users users, String name, String description, boolean isPrivate, String shareLink) {
        this.id = id;
        this.users = users;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.shareLink = shareLink;
    }
}
