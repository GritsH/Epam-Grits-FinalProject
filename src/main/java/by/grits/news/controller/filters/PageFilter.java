package by.grits.news.controller.filters;

import by.grits.news.entities.enums.RoleType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.grits.news.command.PageNavigation.*;
import static by.grits.news.command.SessionAttribute.*;

@WebFilter(urlPatterns = {"/pages/*"})
public class PageFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(PageFilter.class);
    private Set<String> userPages;
    private Set<String> adminPages;
    private Set<String> unknownPages;
    private Set<String> allPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userPages = Stream.of(INDEX, LOGIN, SIGNUP, NEWS_FEED, NEWS_DETAILS).collect(Collectors.toCollection(HashSet::new));
        adminPages = Stream.of(INDEX, LOGIN, NEWS_LIST, NEWS_VIEW, NEWS_ADD, NEWS_EDIT).collect(Collectors.toCollection(HashSet::new));
        unknownPages = Stream.of(INDEX, LOGIN, SIGNUP, NEWS_FEED, NEWS_DETAILS).collect(Collectors.toCollection(HashSet::new));
        allPages = new HashSet<>();
        allPages.addAll(userPages);
        allPages.addAll(adminPages);
        allPages.addAll(unknownPages);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Start PageFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getServletPath();

        if (request.getRequestURI().endsWith(".svg") || request.getRequestURI().endsWith(".css")
                || request.getRequestURI().endsWith(".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        boolean isPageExist = allPages.stream().anyMatch(requestURI::contains);

        if (isPageExist) {
            HttpSession session = request.getSession();
            RoleType role = session.getAttribute(CURRENT_ROLE) == null ?
                    RoleType.UNKNOWN : RoleType.valueOf(session.getAttribute(CURRENT_ROLE).toString());
            boolean isAcceptable = false;
            switch (role) {
                case USER:
                    isAcceptable = userPages.stream().anyMatch(requestURI::contains);
                    break;
                case ADMIN:
                    isAcceptable = adminPages.stream().anyMatch(requestURI::contains);
                    break;
                case UNKNOWN:
                    isAcceptable = unknownPages.stream().anyMatch(requestURI::contains);
            }

            if (!isAcceptable) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            filterChain.doFilter(servletRequest, servletResponse);
            LOGGER.debug("End PageFilter");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
