package WebSpring.demo.repository;

import WebSpring.demo.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    Posts save(Posts posts);

    Optional<Posts> findById(Long id); //조회...

    List<Posts> findAll();
}
