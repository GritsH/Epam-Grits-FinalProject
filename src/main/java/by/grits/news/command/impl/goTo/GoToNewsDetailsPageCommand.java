package by.grits.news.command.impl.goTo;

import by.grits.news.command.*;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.grits.news.command.SessionAttribute.*;

public class GoToNewsDetailsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String id = request.getParameter(RequestParameter.NEWS_ID);
        session.setAttribute(NEWS_ID_SESSION, id);
        return new Router(PageNavigation.NEWS_DETAILS);
    }
}
