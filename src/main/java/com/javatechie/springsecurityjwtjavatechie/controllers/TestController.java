package com.javatechie.springsecurityjwtjavatechie.controllers;

import com.javatechie.springsecurityjwtjavatechie.entities.AuthRequest;
import com.javatechie.springsecurityjwtjavatechie.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private static final Logger logger= LoggerFactory.getLogger(TestController.class);

    @Autowired
    JwtUtil jwtUtil;

//    to authenticate userName and password we need AuthenticationManager
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/")
    public String welcome(){

        return "welcome";
    }

    @RequestMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            logger.info("AuthRequest: "+authRequest.toString());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
            );
        }catch(Exception e){
            throw  new Exception( "INVALID USERNAME AND PASSWORD!!!!!!!");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
