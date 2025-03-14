package com.loco.platform.domain;

import jakarta.persistence.AttributeOverride;
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
@AttributeOverride(name = "updatedAt", column = @Column(insertable = false, updatable = false))
public class PlaceTags extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_tag_id")
    private Long id;

//    @JoinColumn(name = "place_id", nullable = false)
//    private Places places;
//
//    @JoinColumn(name = "tag_id", nullable = false)
//    private Tags tags;

}
