package com.coffee_bean;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public class SpaCsrfTokenRequestHandler extends CsrfTokenRequestAttributeHandler {
    private final CsrfTokenRequestHandler requestHandler = new CsrfTokenRequestAttributeHandler();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {
        this.requestHandler.handle(request, response, csrfToken);
        System.out.println("Handle CSRF: " + csrfToken.get().getToken());
    }

    @Override
    public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
        if (StringUtils.hasText(request.getHeader((csrfToken.getHeaderName())))) {
            return super.resolveCsrfTokenValue(request, csrfToken);
        }
        System.out.println("Resolve CSRF: " + csrfToken.getToken());
        return this.requestHandler.resolveCsrfTokenValue(request, csrfToken);
    }
}
