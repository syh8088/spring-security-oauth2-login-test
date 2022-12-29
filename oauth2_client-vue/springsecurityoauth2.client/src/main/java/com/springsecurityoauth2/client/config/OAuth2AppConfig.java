package com.springsecurityoauth2.client.config;

import com.springsecurityoauth2.client.common.authority.CustomAuthorityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

@Configuration
public class OAuth2AppConfig {

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper(){
        return new CustomAuthorityMapper();
    }

}
