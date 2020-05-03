package com.javatechie.springsecurityjwtjavatechie.filter;

import com.javatechie.springsecurityjwtjavatechie.services.MyUserDetailsService;
import com.javatechie.springsecurityjwtjavatechie.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
//    the request with the token first comes to this filter
//    logic for authenticating the token and validating the user

    private static final Logger logger= LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        logger.info("in Filter");
        String authorizationHeader=httpServletRequest.getHeader("Authorization");

        String token=null;
        String userName = null;
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){

            token = authorizationHeader.substring(7);
            userName=jwtUtil.extractUsername(token);
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);

//            validating the token
            if(jwtUtil.validateToken(token, userDetails)){
//                validating the user details
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

//                setting the user to security context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

//        now register the JwtFilter in securityConfig

    }


}
