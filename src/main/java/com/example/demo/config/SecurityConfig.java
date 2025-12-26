// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;
// import java.util.List;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class SecurityConfig {
    
//     private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }
    
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(authz -> authz
//                 .requestMatchers(
//                     // Auth endpoints
//                     "/api/auth/**",
//                     // Swagger/OpenAPI
//                     "/swagger-ui/**",
//                     "/v3/api-docs/**",
//                     "/swagger-resources/**",
//                     "/webjars/**",
//                     "/swagger-ui.html",
//                     // H2 Console
//                     "/h2-console/**",
//                     // Actuator (if you use it)
//                     "/actuator/**",
//                     // Public API endpoints (if any)
//                     "/api/test/**",
//                     // Allow OPTIONS requests for CORS
//                     "/**/options"
//                 ).permitAll()
//                 // Allow GET requests to catalog without auth (if needed)
//                 .requestMatchers("/api/catalog/**").permitAll()
//                 .requestMatchers("/api/medications/**").permitAll()
//                 .requestMatchers("/api/ingredients/**").permitAll()
//                 // All other requests require authentication
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             )
//             // Add JWT filter
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
//         // For H2 console (if using)
//         http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        
//         return http.build();
//     }
    
//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         // FIX: Use allowedOriginPatterns instead of allowedOrigins
//         configuration.setAllowedOriginPatterns(List.of("*"));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//         configuration.setAllowedHeaders(Arrays.asList(
//             "Authorization",
//             "Content-Type",
//             "X-Requested-With",
//             "Accept",
//             "Origin",
//             "Access-Control-Request-Method",
//             "Access-Control-Request-Headers"
//         ));
//         configuration.setExposedHeaders(List.of(
//             "Authorization",
//             "Content-Type",
//             "Access-Control-Allow-Origin",
//             "Access-Control-Allow-Credentials"
//         ));
//         configuration.setAllowCredentials(true);
//         configuration.setMaxAge(3600L);
        
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
    
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
    
//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration authenticationConfiguration) throws Exception {
//         return authenticationConfiguration.getAuthenticationManager();
//     }
// }

package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        
        return http.build();
    }
}