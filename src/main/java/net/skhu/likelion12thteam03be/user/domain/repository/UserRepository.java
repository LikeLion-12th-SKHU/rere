package net.skhu.likelion12thteam03be.user.domain.repository;

import net.skhu.likelion12thteam03be.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String loginId);
    Optional<User> findByLoginId(String loginId);
}
