package com.loco.platform.repository;

import com.loco.platform.domain.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long> {

    @Query("select r from Rooms r where r.users.id = :userId")
    List<Rooms> findByUserId(@Param("userId") Long userId);

}
