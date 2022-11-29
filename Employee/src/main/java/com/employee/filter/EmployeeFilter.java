/**
 * 
 */
package com.employee.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Hp
 *
 */
@Component
@Slf4j
public class EmployeeFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {
		log.info("inside init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("Start ... dofilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String uri = httpServletRequest.getRequestURI();
		log.info("URI : " + uri);
		
		HttpSession httpSession = httpServletRequest.getSession(true);
		String userName = (String) httpSession.getAttribute("userName");
		log.info("UserName (from session) : "+ userName);
		
		chain.doFilter(request, response);
		
		/*
		if(uri != null && uri.equals("/")) {
			chain.doFilter(request, response);
		} else if(userName != null ) {
			log.info("Valid session....");
			chain.doFilter(request, response);
		} else {
			log.info("In-Valid session....redirect to home page");
			httpServletResponse.sendRedirect("/");
		}
		*/
		
		
		log.info("End ... do filter");
	}

	@Override
	public void destroy() {
		log.info("inside destroy");
	}
}
