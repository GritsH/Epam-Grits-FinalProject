package by.grits.news.command.impl.goTo;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.SessionAttribute;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.grits.news.entities.enums.RoleType.ADMIN;
import static by.grits.news.entities.enums.RoleType.UNKNOWN;

public class GoToNewsFeedPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        Map<String, String> userData = new HashMap<>();
        session.setAttribute(SessionAttribute.USER_DATA_SESSION, userData);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        if(session.getAttribute(SessionAttribute.CURRENT_ROLE) == null){
            session.setAttribute(SessionAttribute.CURRENT_ROLE, UNKNOWN);
        }
        return session.getAttribute(SessionAttribute.CURRENT_ROLE) == ADMIN?
                new Router(PageNavigation.NEWS_LIST) : new Router(PageNavigation.NEWS_FEED);

    }
}
