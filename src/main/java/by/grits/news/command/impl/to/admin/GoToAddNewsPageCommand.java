package by.grits.news.command.impl.to.admin;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.SessionAttribute;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class GoToAddNewsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        session.setAttribute(SessionAttribute.NEWS_DATA_SESSION, newsData);
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return new Router(PageNavigation.NEWS_ADD);
    }
}
