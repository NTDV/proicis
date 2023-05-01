package ru.ntdv.proicis.application;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
            .cors(Customizer.withDefaults())
            //.securityMatcher("/graphql")
            //.csrf(AbstractHttpConfigurer::disable)
            //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            //.authorizeHttpRequests(auth -> auth.requestMatchers("/graphql").authenticated())
            //.httpBasic().authenticationEntryPoint(authenticationEntryPoint()).and()

            .securityMatcher("/files/**", "/graphql")
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .authorizeHttpRequests(auth -> auth.requestMatchers("/files/**", "/graphql").authenticated())
            //.authenticationProvider(authProvider())
            .formLogin(form -> form
                    .loginPage("/user_login").permitAll()
                    .defaultSuccessUrl("/index", false).permitAll()
                    .loginProcessingUrl("/login").permitAll()
                    .failureUrl("/user_login").permitAll())
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
TomcatContextCustomizer sameSiteCookiesConfig() {
    return context -> {
        final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
        cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
        context.setCookieProcessor(cookieProcessor);
    };
}

@Bean
CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOriginPattern("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
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