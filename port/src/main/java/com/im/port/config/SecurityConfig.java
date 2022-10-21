package com.im.port.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.im.port.config.security.handler.OAuth2AuthenticationFailureHandler;
import com.im.port.config.security.handler.OAuth2SuccessHandler;
import com.im.port.config.security.oauth.PrincipalOauth2UserService;
import com.im.port.config.security.oauth.cookieutils.HttpCookieOAuth2AuthorizationRequestRepository;
import com.im.port.config.security.oauth.jwt.JwtAuthenticationEntryPoint;
import com.im.port.config.security.oauth.jwt.JwtRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화
// preAuthorize PostAuthroize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2UserService principalOauth2UserService;
    
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info(" ##### SecurityConfig configure");
        http.cors()
            .and()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .authorizeRequests()
                .antMatchers("/", "/error", "/favicon.ico")
                    .permitAll()
                .antMatchers("/PortChat")
                    .hasRole("MEMBER")
                .anyRequest()
                    .permitAll()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .oauth2Login()
                .authorizationEndpoint()
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                    .baseUri("/oauth2/authorization")
            .and()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
            .and()
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
            .and()
                .successHandler(oAuth2SuccessHandler)
                .failureHandler((AuthenticationFailureHandler) oAuth2AuthenticationFailureHandler);
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
