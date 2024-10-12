package product_inventory_management.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import product_inventory_management.repository.user.IUserRepository;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserRepository userRepository;

    public SecurityFilter(JwtTokenProvider jwtTokenProvider, IUserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {



        Optional<String> tokenOpt = this.extractToken(request);

        tokenOpt.ifPresent(token -> {
            Optional<String> usernameOpt = jwtTokenProvider.validateToken(token);

            usernameOpt.ifPresent(username -> {
                UserDetails user = userRepository.findByUsername(username);
                authenticateUser(user);
            });
        });

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(UserDetails user) {
        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return Optional.of(authorizationHeader.replace("Bearer ", ""));
        }
        return Optional.empty();
    }
}
