/*package com.example.co_voiturage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Allow access to these paths without authentication
                        .requestMatchers("/register", "/Auth").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Specify the custom login page
                        .loginPage("/Auth")
                        .defaultSuccessUrl("/rides", true) // Redirect to /rides upon successful login
                        .permitAll() // Allow everyone to access the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Endpoint for logout
                        .logoutSuccessUrl("/Auth") // Redirect to login page after logout
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .deleteCookies("JSESSIONID") // Remove session cookie
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity; enable in production with proper configuration

        return http.build();
    }
}
*/