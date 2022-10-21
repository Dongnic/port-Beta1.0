package com.im.port.config.security.handler;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.im.port.config.exception.BadRequestException;
import com.im.port.config.security.auth.PrincipalDetails;
import com.im.port.config.security.oauth.cookieutils.CookieUtils;
import com.im.port.config.security.oauth.cookieutils.HttpCookieOAuth2AuthorizationRequestRepository;
import com.im.port.config.security.oauth.jwt.AppProperties;
import com.im.port.config.security.oauth.jwt.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtTokenUtil jwtTokenUtil;

    private final AppProperties appProperties;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	//oauth2인증이 성공적으로 이뤄졌을 때 실행된다 
    //token을 포함한 uri을 생성 후 인증요청 쿠키를 비워주고 redirect 한다. 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info(" ##### onAuthenticationSuccess ");
        String targetUrl = determineTargetUrl(request, response, authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    
    //token을 생성하고 이를 포함한 프론트엔드로의 uri를 생성한다. 
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info(" ##### determineTargetUrl ");
        log.info(" ### authentication.getPrincipal :  " + authentication.getPrincipal());
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info(" ### principalDetails.getEmail() :  " + principalDetails.getEmail());
        Optional<String> redirectUri = CookieUtils.getCookie(request, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        log.info(" ### targetUrl : " + targetUrl);
        String token = jwtTokenUtil.generateToken(principalDetails.getEmail());
        log.info(" ### token : " + token);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }
    
    //인증정보 요청 내역을 쿠키에서 삭제한다. 
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        log.info(" ##### clearAuthenticationAttributes ");
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
    
    //application.properties에 등록해놓은 Redirect uri가 맞는지 확인한다. (app.redirect-uris)
    private boolean isAuthorizedRedirectUri(String uri) {
        log.info(" ##### isAuthorizedRedirectUri ");
        URI clientRedirectUri = URI.create(uri);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                        && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                            log.info(" ### authorizedURI = clientRedirectUri ");
                            return true;
                        }
                    log.info(" ### authorizedURI != clientRedirectUri ");
                    return false;
                });
    }
}