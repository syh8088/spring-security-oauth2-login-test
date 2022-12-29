package com.springsecurityoauth2.client.common.converters;

import com.springsecurityoauth2.client.model.users.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@Setter
public class ProviderUserRequest {

    private final ClientRegistration clientRegistration;
    private final OAuth2User oAuth2User;
    private final User user;

    public ProviderUserRequest(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
        this.clientRegistration = clientRegistration;
        this.oAuth2User = oAuth2User;
        this.user = null;
    };

    public ProviderUserRequest(User user) {
        this.clientRegistration = null;
        this.oAuth2User = null;
        this.user = user;
    };
}
