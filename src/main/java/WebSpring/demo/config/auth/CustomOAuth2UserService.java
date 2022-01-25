/*
package WebSpring.demo.config.auth;

import WebSpring.demo.config.auth.dto.OAuthAttributes;
import WebSpring.demo.config.auth.dto.SessionUser;
import WebSpring.demo.domain.user.User;
import WebSpring.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

//구글 로그인 이후 가져온 사용자의 정보(email,name,picture) 들을 기반으로 가입 및 정보수정, 세션저장등의 기능을 제공
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    //둘 이상의 페이지 요청 또는 웹 사이트 방문에서 사용자를 식별하고 해당 사용자에 대한 정보를 저장하는 방법을 제공합니다.
    private final HttpSession httpSession;
    @Override

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //표준 OAuth 2.0 공급자의 경우 UserInfo 응답에서 사용자 이름에 액세스하는 데 사용되는 속성 이름이 필요하므로 를 통해 사용할 수 있어야 합니다
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //google인지 , naver인지 구분하기 위해
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("registrationId = " + registrationId);
        //OAuth 로그인 진행 시 키가 되는 필드값. primary key와 같은 의미 ( 구글은 기본적으로 코드를 지원 하지만 네이버 카카오는 지원X )
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println("userNameAttributeName = " + userNameAttributeName);

        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스 입니다.
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user",new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                ,attributes.getAttributes()
                ,attributes.getNameAttributeKey()
        );
    }

    //구글 사용자 정보가 업데이트 되었을때 . (ex) 사용자의 이름이나 프로필 사진등)
    public User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}


 */