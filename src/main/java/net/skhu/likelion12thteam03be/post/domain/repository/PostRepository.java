package net.skhu.likelion12thteam03be.post.domain.repository;

import net.skhu.likelion12thteam03be.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
