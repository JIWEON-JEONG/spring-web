package WebSpring.demo.config.auth;

import WebSpring.demo.config.auth.LoginUser;
import WebSpring.demo.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
//HandlerMethodArgumentResolver : 조건에 맞는 경우 , 메소드가 있다면 H.M.A.R 의 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.
public class LoginUserArgumentResolver  implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단한다. [여기서는 @LoginUser 라는 어노테이션이 붙어있는지
    // ,파라미터 클래스 타입이 SessionUser.class 인지 판단]
    public boolean supportsParameter(MethodParameter parameter) {
        boolean inLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return inLoginUserAnnotation&isUserClass;
    }

    @Override
    //파라미터에 전달할 객체를 생성한다.
    //[세션에서 객체를 가져온다]
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
