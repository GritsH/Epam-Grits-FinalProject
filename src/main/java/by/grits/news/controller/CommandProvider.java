package by.grits.news.controller;

import by.grits.news.command.Command;
import by.grits.news.command.CommandType;
import by.grits.news.command.impl.DefaultCommand;
import by.grits.news.command.impl.SignupCommand;
import by.grits.news.command.impl.goTo.GoToLoginPageCommand;
import by.grits.news.command.impl.goTo.GoToSignupPageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static by.grits.news.command.CommandType.*;

public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private static EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);
    static{
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(SIGNUP, new SignupCommand());
        commands.put(GO_TO_SIGNUP_PAGE, new GoToSignupPageCommand());
        commands.put(GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
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
