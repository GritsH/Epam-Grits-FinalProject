package by.grits.news.command.impl.goTo;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.SessionAttribute;
import by.grits.news.command.exception.CommandException;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoToNewsListPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        Map<String, String> userData = new HashMap<>();
        List<News> allNews;
        NewsService newsService = NewsServiceImpl.getInstance();
        try {
            allNews = newsService.findAllNews();
        } catch (ServiceException e) {
            throw new CommandException("Could not retrieve all news "+e);
        }
        session.setAttribute(SessionAttribute.USER_DATA_SESSION, userData);
        session.setAttribute(SessionAttribute.ALL_NEWS_SESSION, allNews);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return new Router(PageNavigation.NEWS_LIST);
    }
}
