package ru.ntdv.proicis.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public
class SecurityConfiguration {
@Autowired
private UserDetailsService userDetailsService;

@Bean
public
SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
    return http
            .cors(AbstractHttpConfigurer::disable)
            .securityMatcher("/graphql")
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .authorizeHttpRequests(auth -> auth.requestMatchers("/graphql").authenticated())
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint()).and()

            .securityMatcher("/files/**")
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .authorizeHttpRequests(auth -> auth.requestMatchers("/files/**").authenticated())
            //.authenticationProvider(authProvider())
            .formLogin(form -> form
                               .loginPage("/user_login").permitAll()
                               .defaultSuccessUrl("/index", false).permitAll()
                               .loginProcessingUrl("/login").permitAll()
                               .failureUrl("/user_login").permitAll()
                      )
            .logout().permitAll().and()
            .rememberMe().and()
            .csrf(AbstractHttpConfigurer::disable)


            .securityMatcher("/**")
            .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
            .csrf(AbstractHttpConfigurer::disable)
            .build();
}

@Bean
public
AuthenticationEntryPoint authenticationEntryPoint() {
    BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
    entryPoint.setRealmName("api_proiics");
    return entryPoint;
}

@Bean
public
AuthenticationManager authenticationManager(final HttpSecurity http) throws Exception {
    return http
            .getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(authProvider())
            .build();
}

@Bean
public
DaoAuthenticationProvider authProvider() {
    final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
}

@Bean
public
PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
}
}