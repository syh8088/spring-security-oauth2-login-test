package com.springsecurityoauth2.client.common.converters;


import com.springsecurityoauth2.client.model.users.ProviderUser;
import com.springsecurityoauth2.client.model.users.User;
import com.springsecurityoauth2.client.model.users.form.FormUser;

public final class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        User user = providerUserRequest.getUser();
        if (user == null) return null;

        return FormUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .email(user.getEmail())
                .provider("none")
                .build();
    }
}
