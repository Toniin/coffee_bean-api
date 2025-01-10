package com.coffee_bean.configuration;

import com.coffee_bean.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final List<String> ALLOWED_ORIGINS = new ArrayList<>() {{
        add("http://localhost:4200");
    }};
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(csrf -> csrf
//                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
//                )
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/auth/signin", "/auth/signup", "/api/products", "/api/categories", "/api/product/{productId}", "/api/category/{categoryId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/product").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
//                .addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins(ALLOWED_ORIGINS)
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders(
//                                HttpHeaders.AUTHORIZATION,
//                                HttpHeaders.CONTENT_TYPE,
//                                "X-XSRF-TOKEN",
//                                HttpHeaders.ACCEPT
//                        )
//            }
//        };
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(ALLOWED_ORIGINS);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-XSRF-TOKEN", "Accept"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
