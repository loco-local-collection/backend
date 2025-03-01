package com.loco.platform.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@AttributeOverride(name = "updatedAt", column = @Column(insertable = false, updatable = false))
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    @NotNull
    @JoinColumn(name = "user_id")
    private Users users;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

}
