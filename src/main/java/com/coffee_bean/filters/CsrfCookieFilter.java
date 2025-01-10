//package com.coffee_bean.filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class CsrfCookieFilter extends OncePerRequestFilter {
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
////        // Render the CSRF token in a cookie
////        csrfToken.getToken();
////        System.out.println("CSRF filter: " +csrfToken.getToken());
////
////        filterChain.doFilter(request, response);
////    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
////        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
//
//        System.out.println("CSRF filter: " + csrfToken.getToken());
////        Cookie cookie = new Cookie("XSRF-TOKENEE", csrfToken.getToken());
////        cookie.setPath("/");
////        cookie.setSecure(true); // Utilisez true en production avec HTTPS
////        cookie.setHttpOnly(false);
////        cookie.setMaxAge(3600);
////        String sameSite = "Lax"; // Ou "Lax" selon vos besoins
////        String headerValue = String.format("%s; SameSite=%s", cookie.toString(), sameSite);
////        response.addHeader("Set-Cookie", headerValue);
//        filterChain.doFilter(request, response);
//    }
//}
