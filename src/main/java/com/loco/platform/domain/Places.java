package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Places extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @JoinColumn(name = "room_id", nullable = false)
    private Rooms rooms;

    @JoinColumn(name = "location_id", nullable = false)
    private Locations locations;

    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
}
