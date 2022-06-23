package by.grits.news.controller.filters;

import by.grits.news.command.CommandType;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.RequestParameter;
import by.grits.news.entities.enums.RoleType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Locale;

import static by.grits.news.command.SessionAttribute.CURRENT_ROLE;

@WebFilter(filterName = "ControllerFilter", urlPatterns = "/controller")
public class ControllerFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(ControllerFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Start ControllerFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        String commandName = httpServletRequest.getParameter(RequestParameter.COMMAND);
        if (commandName == null) {
            httpServletResponse.sendRedirect(PageNavigation.INDEX);
            return;
        }
        try {
            CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
            RoleType roleType = session.getAttribute(CURRENT_ROLE) == null ?
                    RoleType.UNKNOWN : RoleType.valueOf(session.getAttribute(CURRENT_ROLE).toString());
            EnumSet<RoleType> roles = commandType.getAcceptableRole();
            if (roles.contains(roleType)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        LOGGER.debug("End ControllerFilter");
    }
}
