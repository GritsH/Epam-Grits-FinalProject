package by.grits.news.command.impl;

import by.grits.news.command.*;
import by.grits.news.command.exception.CommandException;
import by.grits.news.entities.News;
import by.grits.news.util.NewsDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.grits.news.command.SessionAttribute.*;

public class DisplayNewsDetailsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        News foundNews = NewsDeserializer.deserializeNews(request);
        session.setAttribute(NEWS_DETAILS_SESSION, foundNews);
        return new Router(PageNavigation.NEWS_DETAILS);
    }
}
