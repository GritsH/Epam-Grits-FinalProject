package by.grits.news.command.impl.goTo.admin;

import by.grits.news.command.*;
import by.grits.news.command.exception.CommandException;
import by.grits.news.entities.News;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.grits.news.command.SessionAttribute.NEWS_ID_TO_EDIT_SES;

public class GoToEditNewsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        //List<News> allNews;
        String id = request.getParameter(RequestParameter.NEWS_ID_TO_EDIT);
        session.setAttribute(NEWS_ID_TO_EDIT_SES, id);
        session.setAttribute(SessionAttribute.NEWS_DATA_SESSION, newsData);
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return new Router(PageNavigation.NEWS_EDIT);
    }
}
