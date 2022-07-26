package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import by.grits.news.dao.impl.UserDaoImpl;
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

public class SignupCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SignupCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        UserService userService = UserServiceImpl.getInstance();
        userService.init(UserDaoImpl.getInstance());
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);
        Router router;
        try {
            Integer sizeBefore = userData.size();
            boolean result = userService.signup(userData);
            Integer sizeAfter = userData.size();
            if (sizeAfter.equals(sizeBefore)) {
                session.removeAttribute(USER_DATA_SESSION);
                session.setAttribute(REGISTRATION_RESULT, result);
                router = new Router(PageNavigation.INDEX);
            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
                router = new Router(PageNavigation.SIGNUP);
            }

        } catch (ServiceException e) {
            LOGGER.error("Try to create new account was failed.", e);
            throw new CommandException("Try to create new account was failed.", e);
        }
        return router;
    }

    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_EMAIL_SESSION);
        userData.remove(WRONG_PASSWORD_SESSION);
        userData.remove(MISMATCH_PASSWORDS_SESSION);
        userData.remove(WRONG_EMAIL_EXISTS_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(USER_EMAIL_SESSION, request.getParameter(EMAIL));
        userData.put(PASSWORD_SESSION, request.getParameter(PASS));
        userData.put(REPEAT_PASSWORD_SESSION, request.getParameter(REPEAT_PASSWORD));
    }
}
