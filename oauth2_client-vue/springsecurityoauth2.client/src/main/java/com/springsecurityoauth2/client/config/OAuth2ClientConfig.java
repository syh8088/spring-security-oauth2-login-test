package com.springsecurityoauth2.client.config;

import com.springsecurityoauth2.client.service.CustomOAuth2UserService;
import com.springsecurityoauth2.client.service.CustomOidcUserService;
import com.springsecurityoauth2.client.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ClientConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthSuccessHandler authSuccessHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/static/js/**", "/static/images/**", "/static/css/**","/static/scss/**");
    }

    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
//                .antMatchers("/loginProc").permitAll()
                .antMatchers("/api/user")
                .access("hasAnyRole('SCOPE_profile','SCOPE_profile_image', 'SCOPE_email')")
//                .access("hasAuthority('SCOPE_profile')")
                .antMatchers("/api/oidc")
                .access("hasRole('SCOPE_openid')")
                //.access("hasAuthority('SCOPE_openid')")
                .antMatchers("/")
                .permitAll()
                .anyRequest().authenticated());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable();

//        http.oauth2Client()
//                .

        http.formLogin().loginPage("/login").loginProcessingUrl("/loginProc").successHandler(authSuccessHandler)
//                .defaultSuccessUrl("http://localhost:3000")
                .permitAll();

        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)  // OAuth2
                        .oidcUserService(customOidcUserService)));  // OpenID Connect
        http.userDetailsService(customUserDetailsService);  // Form
        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        http.logout().logoutSuccessUrl("/");
        return http.build();
   }

}
