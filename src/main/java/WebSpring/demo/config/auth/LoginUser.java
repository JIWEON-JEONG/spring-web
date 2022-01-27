package WebSpring.demo.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)   // 이 어노테이션이 생성될 수 있는 위치를 지정한다. [메소드의 파라미터로 선언된 객체에서만 사용가능]
@Retention(RetentionPolicy.RUNTIME) //어노테이션이 얼마나 살아있을지 생명주기를 결정한다.
public @interface LoginUser {
}
