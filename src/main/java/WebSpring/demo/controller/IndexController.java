package WebSpring.demo.controller;

//import WebSpring.demo.config.auth.dto.SessionUser;
import WebSpring.demo.config.auth.LoginUser;
import WebSpring.demo.config.auth.dto.SessionUser;
import WebSpring.demo.controller.dto.PostsResponseDto;
import WebSpring.demo.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    @GetMapping("/")
    //model : 서버 템플릿 엔진에서 사용 할 수 있는 객체를 저장 할 수 있다.
    //findAllDesc() 로 가져온 결과를 posts로 index.mustache에 전달한다.
    //어느 컨트롤러 든지 @LoginUser만 사용하면 세션정보를 가져올 수 있게 되었다.
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser user = user.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    //@PathVariable : URL경로에 변수를 넣어주는 기능
    //model 에 데이터를 담아서 뷰에서 모델 랜더링 해서 사용
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
