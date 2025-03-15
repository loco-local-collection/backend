package com.loco.platform.repository;

import com.loco.platform.domain.Rooms;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoomsActivityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean update(Rooms input) {
        return entityManager.createQuery("UPDATE Rooms r SET r.name = :name, r.description = :description WHERE r.users.id = :userId and r.id=:id")
                .setParameter("name", input.getName())
                .setParameter("description", input.getDescription())
                .setParameter("userId", input.getUsers().getId())
                .setParameter("id", input.getId())
                .executeUpdate() == 1;
    }

    public boolean delete(Long roomId, Long userId) {
        entityManager.createQuery("DELETE FROM RoomTags rt WHERE rt.rooms.id = :roomdId")
            .setParameter("roomdId", roomId)
            .executeUpdate();

        return entityManager.createQuery("DELETE FROM Rooms r WHERE r.users.id = :userId and r.id=:id")
            .setParameter("userId", userId)
            .setParameter("id", roomId)
            .executeUpdate() == 1;
    }
}
