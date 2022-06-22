package by.grits.news.controller;

import by.grits.news.command.Command;
import by.grits.news.command.CommandType;
import by.grits.news.command.impl.*;
import by.grits.news.command.impl.goTo.*;
import by.grits.news.command.impl.goTo.admin.GoToAddNewsPageCommand;
import by.grits.news.command.impl.goTo.admin.GoToEditNewsPageCommand;
import by.grits.news.command.impl.goTo.admin.GoToNewsListPageCommand;
import by.grits.news.command.impl.goTo.admin.GoToNewsViewPageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static by.grits.news.command.CommandType.*;

public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private static EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    static {
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(SIGNUP, new SignupCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(ADD_NEWS, new AddNewsCommand());
        commands.put(EDIT_NEWS, new EditNewsCommand());
        commands.put(DELETE_NEWS, new DeleteNewsCommand());
        commands.put(GO_TO_SIGNUP_PAGE, new GoToSignupPageCommand());
        commands.put(GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(GO_TO_NEWS_PAGE, new GoToNewsFeedPageCommand());
        commands.put(GO_TO_NEWS_LIST_PAGE, new GoToNewsListPageCommand());
        commands.put(GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPageCommand());
        commands.put(GO_TO_NEWS_DETAILS_PAGE, new GoToNewsDetailsPageCommand());
        commands.put(GO_TO_NEWS_VIEW_PAGE, new GoToNewsViewPageCommand());
        commands.put(GO_TO_EDIT_NEWS_PAGE, new GoToEditNewsPageCommand());
    }

    public static Command of(String commandName) {
        Command currentCommand = commands.get(DEFAULT);
        if (commandName != null) {
            try {
                currentCommand = commands.get(CommandType.valueOf(commandName.toUpperCase()));
            } catch (IllegalArgumentException e) {
                LOGGER.error("Command " + commandName + " is not present.", e);
            }
        }
        return currentCommand;
    }
}
