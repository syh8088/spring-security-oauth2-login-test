package com.springsecurityoauth2.client.common.converters;


import com.springsecurityoauth2.client.common.enums.OAuth2Config;
import com.springsecurityoauth2.client.common.util.OAuth2Utils;
import com.springsecurityoauth2.client.model.users.ProviderUser;
import com.springsecurityoauth2.client.model.users.social.GoogleUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

public final class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        ClientRegistration clientRegistration = providerUserRequest.getClientRegistration();

        if (!clientRegistration.getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(
                providerUserRequest.getOAuth2User()),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration());
    }
}
