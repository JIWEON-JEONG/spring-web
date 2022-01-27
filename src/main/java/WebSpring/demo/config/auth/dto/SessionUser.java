package WebSpring.demo.config.auth.dto;

import WebSpring.demo.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//세션에 사용자 정보를 저장하기 위한 DTO 클래스.
@Getter
public class SessionUser implements Serializable {  //객체를 바이트 형태로 바꿔준다.
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}


