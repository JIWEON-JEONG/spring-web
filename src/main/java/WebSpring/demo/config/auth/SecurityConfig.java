package WebSpring.demo.config.auth;

import WebSpring.demo.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //h2 console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                //권한 관리 대상을 지정하는 옵션
                .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                //USER 권한을 가진 사람만 가능
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //나머지 URL들은 모드 인증된 즉, 로그인한 사용자들에게 허용
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                //OAuth2 로그인 기능에 대한 여러 설정 진입점
                .oauth2Login()
                //로그인 성공 이후, 사용자 정보를 가져올때, 설정 담당
                .userInfoEndpoint()
                //소셜 로그인 성공시, 후속조치를 진행할 UserService 인터페이스의 구현체를 등록한다.
                .userService(customOAuth2UserService);
    }
}

