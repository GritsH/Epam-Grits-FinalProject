package by.grits.news.command;

import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String CONTROLLER_PART = "/controller?";

    Router execute(HttpServletRequest request) throws CommandException;

    static String extract(HttpServletRequest request) {
        String commandPart = request.getQueryString();
        return CONTROLLER_PART + commandPart;
    }
}
