package by.grits.news.command;

import by.grits.news.entities.enums.RoleType;

import java.util.EnumSet;

import static by.grits.news.entities.enums.RoleType.*;

public enum CommandType {
    EDIT_NEWS(EnumSet.of(ADMIN)),
    DELETE_NEWS(EnumSet.of(ADMIN)),
    ADD_NEWS(EnumSet.of(ADMIN)),
    GO_TO_LOGIN_PAGE(EnumSet.of(ADMIN, USER, UNKNOWN)),
    GO_TO_SIGNUP_PAGE(EnumSet.of(ADMIN, USER, UNKNOWN)),
    GO_TO_NEWS_PAGE(EnumSet.of(ADMIN, USER, UNKNOWN)),
    GO_TO_NEWS_LIST_PAGE(EnumSet.of(ADMIN)),
    GO_TO_EDIT_NEWS_PAGE(EnumSet.of(ADMIN)),
    GO_TO_ADD_NEWS_PAGE(EnumSet.of(ADMIN)),
    GO_TO_NEWS_DETAILS_PAGE(EnumSet.of(ADMIN,USER,UNKNOWN)),
    GO_TO_NEWS_VIEW_PAGE(EnumSet.of(ADMIN)),
    LOGIN(EnumSet.of(USER, ADMIN)),
    LOGOUT(EnumSet.of(USER, ADMIN)),
    SIGNUP(EnumSet.of(UNKNOWN)),
    DEFAULT(EnumSet.of(ADMIN, USER, UNKNOWN));

    private EnumSet<RoleType> acceptableRole;

    CommandType(EnumSet<RoleType> acceptableRole) {
        this.acceptableRole = acceptableRole;
    }

    public EnumSet<RoleType> getAcceptableRole() {
        return acceptableRole;
    }
}
