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

import static by.grits.news.command.RequestParameter.NEWS_ID_TO_DELETE;

public class DeleteNewsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddNewsCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String getId = request.getParameter(NEWS_ID_TO_DELETE);
        NewsService newsService = NewsServiceImpl.getInstance();
        Router router;
        try{
            boolean result = newsService.deleteNews(Integer.parseInt(getId));
            router = new Router(PageNavigation.NEWS_LIST);
        }catch (ServiceException e) {
            LOGGER.error("Try to delete news was failed.", e);
            throw new CommandException("Try to delete news was failed.", e);
        }
        return  router;
    }
}
