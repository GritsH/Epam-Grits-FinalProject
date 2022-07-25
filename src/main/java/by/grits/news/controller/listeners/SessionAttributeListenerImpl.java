package by.grits.news.controller.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.grits.news.command.SessionAttribute.CURRENT_PAGE;
import static by.grits.news.command.SessionAttribute.CURRENT_USER_EMAIL_SESSION;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionAttributeListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        LOGGER.log(Level.INFO, "Attribute 'user email' was added: " + event.getSession().getAttribute(CURRENT_USER_EMAIL_SESSION));
        LOGGER.log(Level.INFO, "Attribute 'current page' was added: " + event.getSession().getAttribute(CURRENT_PAGE));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        LOGGER.log(Level.INFO, "Attribute 'user email' was replaced: " + event.getSession().getAttribute(CURRENT_USER_EMAIL_SESSION));
        LOGGER.log(Level.INFO, "Attribute 'current page' was replaced: " + event.getSession().getAttribute(CURRENT_PAGE));
    }
}
