package WebSpring.demo.service.posts;

import WebSpring.demo.controller.dto.PostsResponseDto;
import WebSpring.demo.controller.dto.PostsSaveRequestDto;
import WebSpring.demo.controller.dto.PostsUpdateRequestDto;
import WebSpring.demo.domain.posts.Posts;
import WebSpring.demo.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    //생성자로 의존관계 주입
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {  //return id값
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
                );
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }
}
