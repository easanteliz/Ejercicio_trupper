package com.home.trupper.rest.config;

import com.home.trupper.rest.auth.service.JWTService;
import com.home.trupper.rest.service.UserDetailServiceDAO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Configuration
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private final JWTService jwtService;
    private final UserDetailServiceDAO userDetailServiceDAO;

    //para garantizar que el fitro se ejecute solo una vez por cada ejecicion HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                final String token = getTokenFromRequest(request);
                final String userName;
                if (token == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                userName= jwtService.getUsernamefromToken(token);

                if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                    UserDetails userDetails = userDetailServiceDAO.loadUserByUsername(userName);
                    if(jwtService.isTokenValid(token, userDetails)){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);

        }
        return null;
    }

}


