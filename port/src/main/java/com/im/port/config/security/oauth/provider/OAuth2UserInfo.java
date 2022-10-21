package com.im.port.config.security.oauth.provider;

// OAuth2.0 제공자들 마다 응답해주는 속성값이 달라서 공통으로 만들어준다.
public interface OAuth2UserInfo {
	Integer getId();
    String getEmail();
    String getName();
    String getProfileImage();
    ProviderType getProvider();
}
