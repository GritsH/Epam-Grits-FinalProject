package by.grits.news.command.impl.to.admin;

import by.grits.news.command.*;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.grits.news.command.SessionAttribute.NEWS_ID_TO_EDIT_SESSION;

public class ToEditNewsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        String id = request.getParameter(RequestParameter.NEWS_ID_TO_EDIT);
        session.setAttribute(NEWS_ID_TO_EDIT_SESSION, id);
        session.setAttribute(SessionAttribute.NEWS_DATA_SESSION, newsData);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PageNavigation.NEWS_EDIT);
        return new Router(PageNavigation.NEWS_EDIT);
    }
}
