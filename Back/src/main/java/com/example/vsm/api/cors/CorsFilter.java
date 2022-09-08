package com.example.vsm.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.vsm.api.config.property.VSMApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	@Autowired
	private VSMApiProperty vsmApiProperty;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", vsmApiProperty.getOriginPermitida()); 
        response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if ("OPTIONS".equals(request.getMethod()) && vsmApiProperty.getOriginPermitida().equals(request.getHeader("Origin"))) {  //
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        	response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        	response.setHeader("Access-Control-Max-Age", "3600");
			
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}		
	}
	/*
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        
        String origem = request.getHeader("Origin");
        
        if(origem == null) { origem = "*"; }
        
        response.setHeader("Access-Control-Allow-Origin", vsmApiProperty.getOriginPermitida());
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
    	response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
		
	}*/
}
