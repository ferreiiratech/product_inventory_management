package product_inventory_management.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import product_inventory_management.domain.entities.user.roles.UserRoleType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(SecurityFilter securityFilter, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.securityFilter = securityFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-resources/*",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/product/create").hasAuthority(UserRoleType.ADMIN.getRole())
                        .requestMatchers(HttpMethod.PATCH, "/product/*").hasAuthority(UserRoleType.ADMIN.getRole())
                        .requestMatchers(HttpMethod.DELETE, "/product/*").hasAuthority(UserRoleType.ADMIN.getRole())
                        .requestMatchers(HttpMethod.POST, "/category/create").hasAuthority(UserRoleType.ADMIN.getRole())
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAccessDeniedHandler)
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
