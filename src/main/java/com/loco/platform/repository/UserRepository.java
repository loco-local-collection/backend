package com.loco.platform.repository;

import com.loco.platform.domain.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    List<Users> findByNickname(String nickname);
}
