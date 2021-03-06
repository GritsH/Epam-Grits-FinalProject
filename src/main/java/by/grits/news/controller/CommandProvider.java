package by.grits.news.controller;

import by.grits.news.command.Command;
import by.grits.news.command.CommandType;
import by.grits.news.command.impl.*;
import by.grits.news.command.impl.admin.*;
import by.grits.news.command.impl.to.*;
import by.grits.news.command.impl.to.admin.ToAddNewsPageCommand;
import by.grits.news.command.impl.admin.DisplayEditNewsPageCommand;
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
        commands.put(GO_TO_SIGNUP_PAGE, new ToSignupPageCommand());
        commands.put(GO_TO_LOGIN_PAGE, new ToLoginPageCommand());
        commands.put(GO_TO_NEWS_PAGE, new DisplayAllNewsCommand());
        commands.put(GO_TO_NEWS_LIST_PAGE, new DisplayNewsListPageCommand());
        commands.put(GO_TO_ADD_NEWS_PAGE, new ToAddNewsPageCommand());
        commands.put(GO_TO_NEWS_DETAILS_PAGE, new DisplayNewsDetailsCommand());
        commands.put(GO_TO_NEWS_VIEW_PAGE, new DisplayNewsViewPageCommand());
        commands.put(GO_TO_EDIT_NEWS_PAGE, new DisplayEditNewsPageCommand());
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
