package proquartiano.it.proquartianobe.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import proquartiano.it.proquartianobe.entities.admins.Admin;
import proquartiano.it.proquartianobe.entities.admins.AdminsService;
import proquartiano.it.proquartianobe.exceptions.UnauthorizedException;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private AdminsService adminsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Please use Bearer token.");
        } else {
            String token = authHeader.substring(7);
            jwtTools.verifyToken(token);
            String id = jwtTools.extractIdFromToken(token);
            Admin currentAdmin = adminsService.findById(UUID.fromString(id));
            Authentication authentication = new UsernamePasswordAuthenticationToken(currentAdmin, null, currentAdmin.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher matcher = new AntPathMatcher();
        String servletPath = request.getServletPath();
        String method = request.getMethod();

        // TODO: this.
        return matcher.match("/auth/**", servletPath) ||
                "GET".equalsIgnoreCase(method) && matcher.match("/articoli/**", servletPath);
//                matcher.match("/articles/**", request.getServletPath());
//                matcher.match("/categories/**", request.getServletPath()) ||
//                matcher.match("/tags/**", request.getServletPath());
//                matcher.match("/admins/**", request.getServletPath());
    }
}
