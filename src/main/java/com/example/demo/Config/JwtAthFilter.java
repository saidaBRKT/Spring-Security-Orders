package com.example.demo.Config;

//import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            final String authHeader= request.getHeader(AUTHORIZATION);
            final String userEmail;
            final String jwtToken;
            if(authHeader==null || !authHeader.startsWith("Bearer")){
                filterChain.doFilter(request,response);
                return;
            }
            jwtToken=authHeader.substring(7);
            userEmail=jwtUtil.extractUsername(jwtToken);
            if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails= userService.findByEmail(userEmail);
                if (jwtUtil.validateToken(jwtToken,userDetails)){
                    UsernamePasswordAuthenticationToken authToken=
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request,response);
    }
}
