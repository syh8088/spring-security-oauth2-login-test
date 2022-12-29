package com.springsecurityoauth2.client.service;

import com.springsecurityoauth2.client.certification.SelfCertification;
import com.springsecurityoauth2.client.common.converters.ProviderUserConverter;
import com.springsecurityoauth2.client.common.converters.ProviderUserRequest;
import com.springsecurityoauth2.client.model.users.ProviderUser;
import com.springsecurityoauth2.client.model.users.User;
import com.springsecurityoauth2.client.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
//@RequiredArgsConstructor
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SelfCertification certification;

    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    public void selfCertificate(ProviderUser providerUser){
        certification.checkCertification(providerUser);
    }

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {

        User user = userRepository.findByUsername(providerUser.getUsername());

        if (user == null) {
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(), providerUser);
        }
        else {
            System.out.println("userRequest = " + userRequest);
        }
    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.convert(providerUserRequest);
    }

}
