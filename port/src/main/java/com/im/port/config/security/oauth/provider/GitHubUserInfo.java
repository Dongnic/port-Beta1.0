package com.im.port.config.security.oauth.provider;

import java.util.Map;

public class GitHubUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    public GitHubUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    @Override
    public Integer getId() {
        return (Integer) attributes.get("id");
    }
    @Override
    public String getName() {
        return ((String) attributes.get("name") == null) ? (String) attributes.get("login")
                : (String) attributes.get("name");
    }
    @Override
    public String getEmail() {
        return ((String) attributes.get("email") != null) ? (String) attributes.get("email")
                : (String) attributes.get("login") + "@github.com";
    }
    @Override
    public String getProfileImage() {
        return (String) attributes.get("avatar_url");
    }
    @Override
    public ProviderType getProvider() {
        return ProviderType.GITHUB;
    }
}