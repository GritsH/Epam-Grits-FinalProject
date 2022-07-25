package by.grits.news.command.impl.admin;

import by.grits.news.command.*;
import by.grits.news.command.exception.CommandException;
import by.grits.news.entities.News;
import by.grits.news.util.NewsDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.grits.news.command.SessionAttribute.NEWS_DETAILS_SESSION;

public class DisplayEditNewsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        News foundNews = NewsDeserializer.deserializeNews(request);
        session.setAttribute(NEWS_DETAILS_SESSION, foundNews);
        session.setAttribute(SessionAttribute.NEWS_DATA_SESSION, newsData);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PageNavigation.NEWS_EDIT);
        return new Router(PageNavigation.NEWS_EDIT);
    }
}
