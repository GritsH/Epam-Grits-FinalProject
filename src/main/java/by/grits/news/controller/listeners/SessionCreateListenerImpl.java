package by.grits.news.controller.listeners;

import by.grits.news.command.PageNavigation;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static by.grits.news.command.SessionAttribute.CURRENT_PAGE;
import static by.grits.news.command.SessionAttribute.LOCALE;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    private static String DEFAULT_LOCALE = "ru_RU";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(CURRENT_PAGE, PageNavigation.INDEX);
    }
}
