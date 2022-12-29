package com.springsecurityoauth2.client.service;

import com.springsecurityoauth2.client.model.users.ProviderUser;
import com.springsecurityoauth2.client.model.users.User;
import com.springsecurityoauth2.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {

        User user = User.builder().registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .password(providerUser.getPassword())
                .authorities(providerUser.getAuthorities())
                .provider(providerUser.getProvider())
                .email(providerUser.getEmail())
                .picture(providerUser.getPicture())
                .build();

        userRepository.register(user);
    }
}
