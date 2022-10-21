package com.im.port.config.security.oauth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.im.port.config.security.auth.PrincipalDetails;
import com.im.port.repository.UserRepository;
import com.im.port.vo.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public PrincipalDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        log.info(" ##### JwtUserDetailsService loadUserByUsername");
        log.info(" ### useremail : " + useremail);
        UserEntity userEntity = userRepository.findByEmail(useremail);

        if(userEntity != null) return new PrincipalDetails(userEntity);

        throw new UsernameNotFoundException("Could not find user with email : " + useremail);
    }
}