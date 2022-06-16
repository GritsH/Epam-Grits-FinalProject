package by.grits.news.command.impl.goTo;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.SessionAttribute;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToNewsFeedPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return session.getAttribute(SessionAttribute.CURRENT_USER_EMAIL_SESSION) != null
                ? new Router(PageNavigation.NEWS_FEED) : new Router(PageNavigation.LOGIN);
    }
}