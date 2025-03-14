package com.loco.platform.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

//    @JoinColumn(name = "room_id", nullable = false)
//    private Rooms rooms;
//
//    @JoinColumn(name = "user_id", nullable = false)
//    private Users users;

    private String role;

}
