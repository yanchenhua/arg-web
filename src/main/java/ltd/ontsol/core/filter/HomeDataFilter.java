package ltd.ontsol.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import ltd.ontsol.core.dto.HomeDTO;
import ltd.ontsol.core.service.HomeService;

/**
 * This filter is used in production, to put HTTP cache headers with a long (1 month) expiration time.
 * </p>
 */
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "homeDataFilter")
public class HomeDataFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(HomeDataFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }

        ServletContext context = request.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        HomeService homeService = ctx.getBean(HomeService.class);

//        if (session.getAttribute("home") == null) {
//            HomeDTO dto = homeService.findOne();
//            session.setAttribute("home", dto == null ? new HomeDTO() : dto);
//        }
        HomeDTO dto = homeService.findOne();
        session.setAttribute("home", dto == null ? new HomeDTO() : dto);
        session.setAttribute("menuLabel", request.getServletPath());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
