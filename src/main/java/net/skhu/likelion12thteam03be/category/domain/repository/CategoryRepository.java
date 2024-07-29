package net.skhu.likelion12thteam03be.category.domain.repository;

import net.skhu.likelion12thteam03be.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
