package by.grits.news.command.impl.admin;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import by.grits.news.dao.impl.NewsDaoImpl;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static by.grits.news.command.RequestParameter.*;

public class DeleteNewsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddNewsCommand.class);
    private final NewsService newsService;

    public DeleteNewsCommand() {
        newsService = NewsServiceImpl.getInstance();
        newsService.init(NewsDaoImpl.getInstance());
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String newsToDelete = request.getParameter(NEWS_ID_TO_DELETE);
        String[] severalNewsToDelete = request.getParameterValues("checkbox_id");
        Router router;
        try {
            if (severalNewsToDelete == null) {
                newsService.deleteNews(Integer.parseInt(newsToDelete));
            } else {
                for (String id : severalNewsToDelete) {
                    newsService.deleteNews(Integer.parseInt(id));
                }
            }
            router = new Router(PageNavigation.NEWS_LIST);
        } catch (ServiceException e) {
            LOGGER.error("Try to delete news was failed.", e);
            throw new CommandException("Try to delete news was failed.", e);
        }
        return router;
    }

}
