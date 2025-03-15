package com.loco.platform.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.sql.Timestamp;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    protected Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    protected Timestamp updatedAt;

}
