package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "updatedAt", column = @Column(insertable = false, updatable = false))
public class Reports extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @JoinColumn(name = "reported_user_id", referencedColumnName = "user_id", nullable = false)
    private Users reportedUser;

    @JoinColumn(name = "room_id", nullable = false)
    private Rooms rooms;

    @JoinColumn(name = "place_id", nullable = false)
    private Places places;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

}
