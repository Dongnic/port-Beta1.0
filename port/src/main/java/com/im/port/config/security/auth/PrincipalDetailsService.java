package com.im.port.config.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.im.port.repository.UserRepository;
import com.im.port.vo.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티 session(내부 Authentication(UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        log.info("##### PrincipalDetailsService ");
        log.info(" ### email : " + username);
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity != null) {
            log.info(" ### userEntity is not null");
            return new PrincipalDetails(userEntity);
        }
        log.warn(" ### userEntity is null");
        return null;
    }

}
