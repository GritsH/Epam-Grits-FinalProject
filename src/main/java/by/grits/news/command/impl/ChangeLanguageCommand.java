package by.grits.news.command.impl;

import by.grits.news.command.Command;
import by.grits.news.command.RequestParameter;
import by.grits.news.command.Router;
import by.grits.news.command.SessionAttribute;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private enum Language {
        EN("en_EN"),
        RU("ru_RU");
        private String locale;

        Language(String locale) {
            this.locale = locale;
        }

        public String getLocale() {
            return locale;
        }
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = session.getAttribute(SessionAttribute.CURRENT_PAGE).toString();
        Language newLanguage = Language.valueOf(request.getParameter(RequestParameter.LANGUAGE));
        switch (newLanguage) {
            case EN:
                session.setAttribute(SessionAttribute.LOCALE, Language.EN.getLocale());
                break;
            default:
                session.setAttribute(SessionAttribute.LOCALE, Language.RU.getLocale());
        }
        return new Router(currentPage);
    }
}
