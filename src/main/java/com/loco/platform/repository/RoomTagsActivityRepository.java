package com.loco.platform.repository;

import com.loco.platform.domain.Tags;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomTagsActivityRepository {

    private final EntityManager entityManager;

    public List<Tags> findTagsByName(List<String> tagNames) {
        return entityManager.createQuery("select t from Tags t where t.name in :tagNames", Tags.class)
            .setParameter("tagNames", tagNames)
            .getResultList();
    }
}
