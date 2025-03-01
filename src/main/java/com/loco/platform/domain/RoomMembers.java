package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@AttributeOverrides({
    @AttributeOverride(name = "createdAt", column = @Column(name = "joined_at", nullable = false, updatable = false)), // createdAt -> joinedAt 변경
    @AttributeOverride(name = "updatedAt", column = @Column(insertable = false, updatable = false)) // updatedAt 제외
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
