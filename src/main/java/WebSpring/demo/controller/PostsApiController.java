package WebSpring.demo.controller;

import WebSpring.demo.controller.dto.PostsResponseDto;
import WebSpring.demo.controller.dto.PostsSaveRequestDto;
import WebSpring.demo.controller.dto.PostsUpdateRequestDto;
import WebSpring.demo.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor    //private 으로 생성한 필드 생성자 자동생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")    //등록되어 있는 유저 정보를 삭제하기 위한 로직
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;  // 굳이 id를 리턴할 필요가 있을까?
    }
}
