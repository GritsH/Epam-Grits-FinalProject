package by.grits.news.command.impl.admin;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.SessionAttribute;
import by.grits.news.command.exception.CommandException;
import by.grits.news.dao.impl.NewsDaoImpl;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayNewsListPageCommand implements Command {
    private final NewsService newsService;

    public DisplayNewsListPageCommand() {
        newsService = NewsServiceImpl.getInstance();
        newsService.init(NewsDaoImpl.getInstance());
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();
        List<News> allNews;
        try {
            allNews = newsService.findAllNews();
            allNews.sort(Collections.reverseOrder());
        } catch (ServiceException e) {
            throw new CommandException("Could not retrieve all news " + e);
        }
        session.setAttribute(SessionAttribute.USER_DATA_SESSION, userData);
        session.setAttribute(SessionAttribute.ALL_NEWS_SESSION, allNews);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PageNavigation.NEWS_LIST);
        return new Router(PageNavigation.NEWS_LIST);
    }
}
