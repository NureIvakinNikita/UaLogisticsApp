package nikita.ivakin.apzpzpi215ivakinnikitatask2.config;

import lombok.RequiredArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.token.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/api/brig-com/**").hasAnyRole("BRIGADE_COMMANDER", "ADMIN")
                            .requestMatchers("/api/bat-com/**").hasAnyRole("BATTALION_COMMANDER", "ADMIN")
                            .requestMatchers("/api/com-com/**").hasAnyRole("COMPANY_COMMANDER", "ADMIN")
                            .requestMatchers("/api/plat-com/**").hasAnyRole("PLAT_COMMANDER", "ADMIN")
                            .requestMatchers("/api/log-com/**").hasAnyRole("LOGISTIC_COMMANDER", "ADMIN")
                            .requestMatchers("/api/device/**").hasAnyRole("SCANNING_DEVICE")
                            .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
                            .anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}
