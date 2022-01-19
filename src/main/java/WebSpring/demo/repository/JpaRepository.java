package WebSpring.demo.repository;

import WebSpring.demo.domain.posts.Posts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaRepository implements PostsRepository{

    private EntityManager em;

    public JpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Posts save(Posts posts) {
        em.persist(posts);
        return posts;
    }

    @Override
    public Optional<Posts> findById(Long id) {
        Posts posts = em.find(Posts.class, id);
        return Optional.ofNullable(posts);
    }

    @Override
    public List<Posts> findAll() {
        return em.createQuery("select m from Posts m",Posts.class)
                .getResultList();
    }
}
