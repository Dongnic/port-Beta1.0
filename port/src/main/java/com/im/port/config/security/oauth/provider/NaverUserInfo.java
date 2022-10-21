package com.im.port.config.security.oauth.provider;

import java.util.Map;

@SuppressWarnings("unchecked")
public class NaverUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Integer getId() {
        return (Integer) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributes.get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getProfileImage() {
        return (String) attributes.get("profile_image");
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.NAVER;
    }

}
