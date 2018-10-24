package ltd.ontsol.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This filter is used in production, to put HTTP cache headers with a long (1 month) expiration time.
 * </p>
 */
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "crossDomainHttpHeadersFilter")
public class CrossDomainHttpHeadersFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(CrossDomainHttpHeadersFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) httpServletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) httpServletResponse;

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,X-Custom-Header");
        httpResponse.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
