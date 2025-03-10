package com.loco.platform.repository;

import com.loco.platform.domain.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long> {
}
