package duo.cmr.willagropastoral.web.configurations.securyty;

import duo.cmr.willagropastoral.web.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${willagropastoral.admins}")
    private final List<String> admins;
    @Value("${willagropastoral.leaders}")
    private final List<String> leaders;
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        HttpSecurity security = http.authorizeRequests(a -> a.antMatchers(
                        "/maileingabe", "/notifications", "/", "/registration", "/registration/*",
                        "/registration/confirm/*", "/delete/confirm"
                )
                .permitAll()
                .anyRequest()
                .authenticated());
        security
                .logout()
                .clearAuthentication(true)
                .deleteCookies()
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .oauth2Login().userInfoEndpoint().userService(createUserService());
        //security
                //.formLogin();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> createUserService() {
        return new OAuth2UserService<>() {

            final DefaultOAuth2UserService defaultService = new DefaultOAuth2UserService();

            @Override
            public OAuth2User loadUser(OAuth2UserRequest userRequest)
                    throws OAuth2AuthenticationException {
                OAuth2User oauth2User = defaultService.loadUser(userRequest);

                Map<String, Object> attributes = oauth2User.getAttributes(); //keep existing attributes
                Map<String, Object> extendedAttributes = new HashMap<>(attributes);

                Set<GrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                String login = attributes.get("login").toString();

                if (admins.contains(login)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }
                if (leaders.contains(login)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_PARTNER"));
                }
                return new DefaultOAuth2User(authorities, extendedAttributes, "login");
            }
        };
    }

    /**
     * selbst von gestern
     * @param auth
     * @throws Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
