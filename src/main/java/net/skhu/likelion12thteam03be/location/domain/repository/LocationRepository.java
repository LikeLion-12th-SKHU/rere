package net.skhu.likelion12thteam03be.location.domain.repository;

import net.skhu.likelion12thteam03be.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
