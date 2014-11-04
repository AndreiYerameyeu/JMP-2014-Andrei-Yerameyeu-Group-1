package com.epam.web.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.epam.common.exception.WebException;

public class ExceptionHandler extends OncePerRequestFilter {
    
    private final static Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException se) {
            Throwable ex = getException(se);
            ExceptionHandler.LOG.error(ex.getMessage(), ex);
            redirectHome(request, response, ex);
        } catch (Throwable t) {
            ExceptionHandler.LOG.error(t.getMessage(), t);
            redirectError(request, response, t);
        }
    }
    
    private void redirectHome(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws ServletException, IOException {
        request.setAttribute("exception", ex);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
        dispatcher.forward(request, response);
    }
    
    private void redirectError(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws ServletException, IOException {
        request.setAttribute("exception", ex);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
        dispatcher.forward(request, response);
    }
    
    private Throwable getException(ServletException se) {
        Throwable rootCause = se.getRootCause();
        if (null != rootCause && Objects.equals(WebException.class, rootCause.getClass())) {
            return rootCause;
        } else {
            return new WebException("General internal error", se);
        }
    }
}
