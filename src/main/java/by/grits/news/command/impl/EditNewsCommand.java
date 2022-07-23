package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import by.grits.news.dao.impl.NewsDaoImpl;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.grits.news.command.RequestParameter.*;
import static by.grits.news.command.SessionAttribute.*;
import static by.grits.news.command.SessionAttribute.NEWS_AUTHOR_SESSION;

public class EditNewsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddNewsCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> newsData = (Map<String, String>) session.getAttribute(NEWS_DATA_SESSION);
        NewsService newsService = NewsServiceImpl.getInstance();
        updateNewsDataFromRequest(request, newsData);
        Router router;
        try {
            newsService.init(NewsDaoImpl.getInstance());
            newsService.updateNews(newsData);
            session.removeAttribute(NEWS_DATA_SESSION);
            router = new Router(PageNavigation.NEWS_LIST);
        } catch (ServiceException e) {
            LOGGER.error("Try to edit news was failed.", e);
            throw new CommandException("Try to edit news was failed.", e);
        }
        return router;
    }

    private void updateNewsDataFromRequest(HttpServletRequest request, Map<String, String> newsData) {
        newsData.put(NEWS_ID_TO_EDIT_SESSION, request.getParameter(NEWS_ID_TO_EDIT));
        newsData.put(NEWS_TITLE_SESSION, request.getParameter(NEWS_TITLE));
        newsData.put(NEWS_SUMMARY_SESSION, request.getParameter(NEWS_SUMMARY));
        newsData.put(NEWS_CONTENT_SESSION, request.getParameter(NEWS_CONTENT));
        newsData.put(NEWS_ADDED_AT_SESSION, request.getParameter(NEWS_ADDED_AT));
        newsData.put(NEWS_AUTHOR_SESSION, request.getParameter(NEWS_AUTHOR));
    }
}
