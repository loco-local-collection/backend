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

    @NotNull
    @JoinColumn(name = "user_id")
    private Users users;

    @NotNull
    @JoinColumn(name = "reported_user_id", referencedColumnName = "user_id")
    private Users reportedUser;

    @NotNull
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    @NotNull
    @JoinColumn(name = "place_id")
    private Places places;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

}
