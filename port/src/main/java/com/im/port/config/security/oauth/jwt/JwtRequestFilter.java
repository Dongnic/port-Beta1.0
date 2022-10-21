package com.im.port.config.security.oauth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.im.port.config.security.auth.PrincipalDetails;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // log.info("##### doFilterInternal");
        //아래 경로는 이 필터가 적용되지 않는다.
        if (request.getRequestURI().startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");
        // log.info(" ### requestTokenHeader : " + requestTokenHeader);
        String username = null;
        String jwtToken = null;
        
        //Header에서 Bearer 부분 이하로 붙은 token을 파싱한다.
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            //username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            log.info("### jwtToken : " + jwtToken);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.error("### IllegalArgumentException Unable to get JWT Token : " + e);
            } catch (ExpiredJwtException e) {
                log.error("### ExpiredJwtException JWT Token has expired : " + e);
            }
        } else {
            // logger.warn("JWT Token does not begin with Bearer String");
        }
        // log.info("JwtRequestFilter userEmail : " + username);
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            PrincipalDetails principalDetails = jwtUserDetailsService.loadUserByUsername(username);
            log.info("JwtRequestFilter principalDetails : "+principalDetails);
            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, principalDetails)) {
               log.info("jwtTokenUtil.validateToken() : true");
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                   principalDetails, null, principalDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            log.info("jwtTokenUtil.validateToken() : false");
        }
        chain.doFilter(request, response);
    }
}