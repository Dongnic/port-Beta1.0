package com.im.port.config.security.oauth.factory;

import java.util.Map;

import com.im.port.config.security.oauth.provider.GitHubUserInfo;
import com.im.port.config.security.oauth.provider.GoogleUserInfo;
import com.im.port.config.security.oauth.provider.KakaoUserInfo;
import com.im.port.config.security.oauth.provider.NaverUserInfo;
import com.im.port.config.security.oauth.provider.OAuth2UserInfo;
import com.im.port.config.security.oauth.provider.ProviderType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        log.info(" ##### OAuth2UserInfoFactory");
        switch (providerType) {
            case GOOGLE: return new GoogleUserInfo(attributes);
            case GITHUB: return new GitHubUserInfo(attributes);
            case NAVER: return new NaverUserInfo(attributes);
            case KAKAO: return new KakaoUserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
