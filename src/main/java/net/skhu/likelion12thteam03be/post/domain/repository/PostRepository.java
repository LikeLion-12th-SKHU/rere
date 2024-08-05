package net.skhu.likelion12thteam03be.post.domain.repository;

import net.skhu.likelion12thteam03be.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select distinct p from Post p join fetch p.moods m where m.moodId = :moodId ")
    List<Post> findByMoodId(Long moodId);

    @Query("select distinct p from Post p where p.title like :input or p.content like :input ")
    List<Post> findByInput(String input);
}
