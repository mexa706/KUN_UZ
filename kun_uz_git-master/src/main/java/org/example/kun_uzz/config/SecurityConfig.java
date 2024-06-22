package org.example.kun_uzz.config;

import org.example.kun_uzz.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;


    }


    @Bean
    public PasswordEncoder passwordEncoder() { //
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5.getMD5(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        // authorization
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {

            authorizationManagerRequestMatcherRegistry
                    //swager
                    .requestMatchers("/v2/api-docs").permitAll()
                    .requestMatchers("/v3/api-docs").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/swagger-resources").permitAll()
                    .requestMatchers("/swagger-resources/**").permitAll()
                    .requestMatchers("/configuration/ui").permitAll()
                    .requestMatchers("/configuration/security").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/webjars/**").permitAll()
                    .requestMatchers("/swagger-ui.html").permitAll()


                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/profile/create").hasRole("ADMIN")
                    .requestMatchers("/profile/update/*").hasRole("ADMIN")
                    .requestMatchers("/region/lang").permitAll()
                    .requestMatchers("/attach/**").permitAll()
                    .requestMatchers("/region/adm/**").hasRole("ADMIN")
                    .requestMatchers("/article/moderator/**", "/article/moderator")
                    .hasRole("MODERATOR").anyRequest().authenticated();
        });
//        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.build();

    }





}
