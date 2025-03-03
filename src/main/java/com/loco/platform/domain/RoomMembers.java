package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
    @AttributeOverride(name = "createdAt", column = @Column(name = "joined_at", nullable = false, updatable = false)),
    @AttributeOverride(name = "updatedAt", column = @Column(insertable = false, updatable = false))
})
public class RoomMembers extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_member_id")
    private Long id;

    @NotNull
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    @NotNull
    @JoinColumn(name = "user_id")
    private Users users;

    private String role;

}
