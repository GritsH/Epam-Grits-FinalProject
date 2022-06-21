package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.grits.news.command.RequestParameter.*;
import static by.grits.news.command.RequestParameter.NEWS_AUTHOR;
import static by.grits.news.command.SessionAttribute.*;

public class DeleteNewsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddNewsCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String newsToDelete = request.getParameter(NEWS_ID_TO_DELETE);
        String[] severalNewsToDelete = request.getParameterValues("checkbox_id");
        //Map<String, String> severalNewsToDelete = (Map<String, String>) session.getAttribute(SEVERAL_NEWS_TO_DELETE_SESSION);
        NewsService newsService = NewsServiceImpl.getInstance();
        //updateNewsDataFromRequest(request, severalNewsToDelete);
        Router router;
        try {
            if (severalNewsToDelete == null) {
                boolean result = newsService.deleteNews(Integer.parseInt(newsToDelete));
            } else {
                for (String id : severalNewsToDelete) {
                    boolean result = newsService.deleteNews(Integer.parseInt(id));
                }
            }
            router = new Router(PageNavigation.NEWS_LIST);
        } catch (ServiceException e) {
            LOGGER.error("Try to delete news was failed.", e);
            throw new CommandException("Try to delete news was failed.", e);
        }
        return router;
    }

    private void updateNewsDataFromRequest(HttpServletRequest request, Map<String, String> newsData) {
        newsData.put(SEVERAL_NEWS_TO_DELETE_SESSION, request.getParameter(SEVERAL_NEWS_TO_DELETE));
    }
}
