package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.PageNavigation;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.grits.news.command.SessionAttribute.*;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;
        if(session.getAttribute(CURRENT_ROLE) !=null){
            session.setAttribute(CURRENT_PAGE, PageNavigation.NEWS_FEED);
            router = new Router(PageNavigation.NEWS_FEED);
        }else{
            session.setAttribute(CURRENT_PAGE, PageNavigation.LOGIN);
            router = new Router(PageNavigation.LOGIN);
        }
        return router;
    }
}
