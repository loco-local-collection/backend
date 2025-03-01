package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@AttributeOverride(name = "updatedAt", column = @Column(insertable = false, updatable = false))
public class Favorites extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @NotNull
    @JoinColumn(name = "user_id")
    private Users users;

    @NotNull
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    @Column(name = "is_active")
    private boolean isActive = true;

}
