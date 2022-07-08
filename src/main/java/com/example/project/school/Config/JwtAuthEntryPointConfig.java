package com.example.project.school.Config;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthEntryPointConfig implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException authenticationException)throws IOException,ServerException{
                                            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"VOCÊ ESTA SEM ACESSO !");
                                        }
}
