package com.springsecurityoauth2.client.common.converters;


import com.springsecurityoauth2.client.common.enums.OAuth2Config;
import com.springsecurityoauth2.client.common.util.OAuth2Utils;
import com.springsecurityoauth2.client.model.users.ProviderUser;
import com.springsecurityoauth2.client.model.users.social.NaverUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

public final class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        ClientRegistration clientRegistration = providerUserRequest.getClientRegistration();

        if (!clientRegistration.getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(
                OAuth2Utils.getSubAttributes(providerUserRequest.getOAuth2User(), "response"),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration());
    }
}
