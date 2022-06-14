package by.grits.news.command;

import static by.grits.news.command.Router.PageChangeType.*;

public class Router {
    private String page = "DEFAULT";
    private PageChangeType type = FORWARD;

    public enum PageChangeType {
        FORWARD, REDIRECT;
    }

    public Router() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = (page != null ? page : "DEFAULT");
    }

    public void setRedirect() {
        this.type = REDIRECT;
    }

    public void setForward() {
        this.type = FORWARD;
    }

    public PageChangeType getType() {
        return type;
    }
}
