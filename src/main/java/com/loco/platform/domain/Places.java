package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Places extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @NotNull
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    @NotNull
    @JoinColumn(name = "location_id")
    private Locations locations;

    @NotNull
    @JoinColumn(name = "added_id", referencedColumnName = "user_id")
    private Users users;
}
