
package org.example.kun_uzz.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.kun_uzz.DTO.JwtDTO;
import org.example.kun_uzz.util.JwtUtill;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@Component
public class TokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Message", "Token Not Found Mazgi.");
            return;
        }

        String token = authHeader.substring(7);
        JwtDTO jwtDto;

        try {
            jwtDto = JwtUtill.decode(token);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Message", "Token Not Valid");
            return;
        }

        request.setAttribute("id", jwtDto.getId());
        request.setAttribute("role", jwtDto.getRole());
        filterChain.doFilter(request, response);
    }




}
