package com.internetadvert.edu.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter{

	  @Override
	    public void init(FilterConfig config)
	    {
	        
	    }
	    
	    @Override
	    public void destroy()
	    {
	        
	    }
	    
	     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	        chain.doFilter(new XSSWrapper((HttpServletRequest) request), response);
	    }
	
}
