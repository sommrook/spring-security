package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화, prePostEnabled: preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig {

    // 해당 메서들의 리턴되는 오브젝트를 IoC로 등록 해준다.
    @Bean
    public BCryptPasswordEncoder encoderPwd(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                        .requestMatchers("/manager/**").access(new WebExpressionAuthorizationManager("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')"))
                        .requestMatchers("/admin/**").access(new WebExpressionAuthorizationManager("hasRole('ROLE_ADMIN')"))
                        .anyRequest().permitAll())
                .formLogin(loginConfigurer ->
                        loginConfigurer.loginPage("/loginForm")
                                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                                .defaultSuccessUrl("/")); // 로그인 완료 후 이동되는 주소
        return http.build();
    }
}
