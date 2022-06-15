package by.grits.news.controller.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionAttributeListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        LOGGER.log(Level.INFO, "Attribute was added: " + event.getSession().getAttribute("user_name"));
        LOGGER.log(Level.INFO, "Attribute was added: " + event.getSession().getAttribute("current_page"));    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        LOGGER.log(Level.INFO, "Attribute was replaced: " + event.getSession().getAttribute("user_name"));
        LOGGER.log(Level.INFO, "Attribute was replaced: " + event.getSession().getAttribute("current_page"));    }
}
