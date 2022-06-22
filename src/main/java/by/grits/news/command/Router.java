package by.grits.news.command;

import static by.grits.news.command.Router.PageChangeType.*;

public class Router {
    private String page;
    private PageChangeType type = FORWARD;

    public enum PageChangeType {
        FORWARD, REDIRECT;
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, PageChangeType type) {
        this.page = page;
        if (type != null) {
            this.type = type;
        }
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
