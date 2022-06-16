package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import by.grits.news.entities.User;
import by.grits.news.service.UserService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.grits.news.command.RequestParameter.*;
import static by.grits.news.command.SessionAttribute.*;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);
        UserService userService = UserServiceImpl.getInstance();
        Router router;
        try {
            User user = userService.login(userData);
            if (user != null) {
                session.removeAttribute(USER_DATA_SESSION);
                session.setAttribute(CURRENT_USER_EMAIL_SESSION, user.getEmailAddress());
                session.setAttribute(CURRENT_ROLE, user.getRole());
                session.setAttribute(CURRENT_PAGE, PageNavigation.NEWS_FEED);
                router = new Router(PageNavigation.NEWS_FEED);
            }else{
                session.setAttribute(USER_DATA_SESSION, userData);
                session.setAttribute(CURRENT_PAGE, PageNavigation.SIGNUP);
                router = new Router(PageNavigation.SIGNUP);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }

    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_EMAIL_OR_PASSWORD_SESSION);
        userData.remove(NOT_FOUND_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(USER_EMAIL_SESSION, request.getParameter(EMAIL));
        userData.put(PASSWORD_SESSION, request.getParameter(PASS));
    }
}
