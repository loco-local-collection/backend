package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Rooms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @NotNull
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_private")
    private boolean isPrivate = false;

    @Column(name = "share_link", unique = true)
    private String shareLink;

}
