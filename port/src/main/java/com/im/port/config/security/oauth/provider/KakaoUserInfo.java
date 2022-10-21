package com.im.port.config.security.oauth.provider;

import java.util.Map;

@SuppressWarnings("unchecked")
public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;
    private Map<String, Object> attributesProfile;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
    }

    @Override
    public Integer getId() {
        return (Integer) attributes.get("id");
    }

    @Override
    public String getName() {
        return (String) attributesProfile.get("nickname");
    }

    @Override
    public String getEmail() {
        return ((String) attributesAccount.get("email") != null) ? (String) attributesAccount.get("email")
                : (String) attributesAccount.get("login") + "@kakao.com";
    }

    @Override
    public String getProfileImage() {
        return (String) attributesProfile.get("profile_image_url");
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.KAKAO;
    }

}
