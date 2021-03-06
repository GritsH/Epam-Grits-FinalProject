package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import by.grits.news.dao.impl.UserDaoImpl;
import by.grits.news.entities.User;
import by.grits.news.service.UserService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.UserServiceImpl;
import by.grits.news.util.PasswordEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;

import static by.grits.news.command.RequestParameter.*;
import static by.grits.news.command.SessionAttribute.*;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        if (userData != null) {
            removeTempData(userData);
            updateUserDataFromRequest(request, userData);
        }
        UserService userService = UserServiceImpl.getInstance();
        userService.init(UserDaoImpl.getInstance());
        Router router = null;
        try {
            PasswordEncoder encoder = new PasswordEncoder();
            String encodedPass = encoder.encode(userData.get("password_ses"));
            User user = userService.login(userData);
            if (user == null) {
                user = userService.findUserByEmail(userData.get("email_ses"));
            }
            if (Objects.equals(user.getEmailAddress(), userData.get("email_ses"))
                    && Objects.equals(user.getPassword(), encodedPass)) {
                session.removeAttribute(USER_DATA_SESSION);
                session.setAttribute(CURRENT_USER_EMAIL_SESSION, user.getEmailAddress());
                session.setAttribute(CURRENT_ROLE, user.getRole());
                session.setAttribute(CURRENT_PAGE, PageNavigation.NEWS_FEED);
                router = new Router(PageNavigation.NEWS_FEED);
            } else if (Objects.equals(user.getEmailAddress(), userData.get("email_ses"))
                    && !Objects.equals(user.getPassword(), encodedPass)) {
                session.setAttribute(USER_DATA_SESSION, userData);
                session.setAttribute(CURRENT_PAGE, PageNavigation.LOGIN);
                router = new Router(PageNavigation.LOGIN);
            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
                session.setAttribute(CURRENT_PAGE, PageNavigation.SIGNUP);
                router = new Router(PageNavigation.SIGNUP);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Smth wrong with encoding password");
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
