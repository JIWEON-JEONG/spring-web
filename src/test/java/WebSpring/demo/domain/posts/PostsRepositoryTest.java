package WebSpring.demo.domain.posts;

import WebSpring.demo.controller.dto.PostsUpdateRequestDto;
import WebSpring.demo.repository.PostsRepository;
import WebSpring.demo.service.posts.PostsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void getBoardInfo(){
        //given
        String title = "Test title";
        String content = "Test content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("정지원")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
        Assertions.assertThat(posts.getAuthor()).isEqualTo("정지원");
    }

    @Test
    public void baseTimeEntityRegistration(){
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("Test title")
                .content("Test content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println("posts.getCreateDate() = " + posts.getCreateDate());
        System.out.println("posts.getModifiedDate() = " + posts.getModifiedDate());

        Assertions.assertThat(posts.getCreateDate()).isAfter(now);
        Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
    }

    @Test   //질문
    public void updatePosts(){
        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                .title("Test update title")
                .content("Test update content")
                .build();

        PostsService postsService = new PostsService(postsRepository);
        postsService.update(7L, postsUpdateRequestDto);

    }

}
