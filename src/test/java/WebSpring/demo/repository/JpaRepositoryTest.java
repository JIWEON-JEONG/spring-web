package WebSpring.demo.repository;

import WebSpring.demo.domain.posts.Posts;
import WebSpring.demo.service.posts.PostsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class JpaRepositoryTest {
}
