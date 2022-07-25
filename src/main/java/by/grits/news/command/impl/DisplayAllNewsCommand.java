package by.grits.news.command.impl;

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

import java.util.*;

import static by.grits.news.entities.enums.RoleType.ADMIN;
import static by.grits.news.entities.enums.RoleType.UNKNOWN;

public class DisplayAllNewsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        Map<String, String> userData = new HashMap<>();
        String sortType = request.getParameter("sort_type");
        List<News> allNews;
        NewsService newsService = NewsServiceImpl.getInstance();
        try {
            newsService.init(NewsDaoImpl.getInstance());
            allNews = newsService.findAllNews();
            if (Objects.equals(sortType, "asc")) {
                Collections.sort(allNews);
            } else {
                Collections.sort(allNews, Collections.reverseOrder());
            }
        } catch (ServiceException e) {
            throw new CommandException("Could not retrieve all news " + e);
        }
        session.setAttribute(SessionAttribute.USER_DATA_SESSION, userData);
        session.setAttribute(SessionAttribute.ALL_NEWS_SESSION, allNews);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        if (session.getAttribute(SessionAttribute.CURRENT_ROLE) == null) {
            session.setAttribute(SessionAttribute.CURRENT_ROLE, UNKNOWN);
        }
        return session.getAttribute(SessionAttribute.CURRENT_ROLE) == ADMIN ?
                new Router(PageNavigation.NEWS_LIST) : new Router(PageNavigation.NEWS_FEED);

    }
}
