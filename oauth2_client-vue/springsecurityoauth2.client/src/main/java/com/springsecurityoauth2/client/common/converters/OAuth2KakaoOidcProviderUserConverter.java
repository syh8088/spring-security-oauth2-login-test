package com.springsecurityoauth2.client.common.converters;

import com.springsecurityoauth2.client.common.enums.OAuth2Config;
import com.springsecurityoauth2.client.common.util.OAuth2Utils;
import com.springsecurityoauth2.client.model.users.ProviderUser;
import com.springsecurityoauth2.client.model.users.social.KakaoOidcUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public final class OAuth2KakaoOidcProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        ClientRegistration clientRegistration = providerUserRequest.getClientRegistration();
        OAuth2User oAuth2User = providerUserRequest.getOAuth2User();

        if (!clientRegistration.getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        if (!(oAuth2User instanceof OidcUser)) {
            return null;
        }

        return new KakaoOidcUser(OAuth2Utils.getMainAttributes(
                providerUserRequest.getOAuth2User()),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration());
    }
}
