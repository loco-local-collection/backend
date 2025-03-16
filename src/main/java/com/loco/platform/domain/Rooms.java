package com.loco.platform.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    //@Lob
    // 큰 데이터를 한개의 table에 두지않고 여러 table에 나누어둠 -> 여러 table의 값을 필요한 부분만 나누어 읽는 기술을 사용할 때 사용됨
    // 일반 select 문 조회시 오류 발생 -> 제외시킴
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @OneToMany(mappedBy = "rooms",fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<RoomTags> tags = new ArrayList<>();

    @Column(name = "is_private")
    private boolean isPrivate = false;

    @Column(name = "share_link", unique = true)
    private String shareLink;

    @Builder
    public Rooms(Long id, Users users, String name, String description,
        boolean isPrivate, String shareLink) {
        this.id = id;
        this.users = users;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.shareLink = shareLink;
    }

    public void addTags(List<RoomTags> roomTags) {
        this.tags.addAll(roomTags);
    }
}