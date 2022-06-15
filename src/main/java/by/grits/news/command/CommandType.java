package by.grits.news.command;

import by.grits.news.entities.enums.RoleType;

import java.util.EnumSet;

import static by.grits.news.entities.enums.RoleType.*;

public enum CommandType {
    EDIT_NEWS(EnumSet.of(ADMIN)),
    DELETE_NEWS(EnumSet.of(ADMIN)),
    GO_TO_LOGIN_PAGE(EnumSet.of(ADMIN, USER, UNKNOWN)),
    GO_TO_SIGNUP_PAGE(EnumSet.of(ADMIN, USER, UNKNOWN)),
    GO_TO_NEWS_PAGE(EnumSet.of(ADMIN, USER, UNKNOWN)),
    LOGIN(EnumSet.of(USER)),
    LOGOUT(EnumSet.of(USER, ADMIN)),
    SIGNUP(EnumSet.of(UNKNOWN));

    private EnumSet<RoleType> acceptableRole;

    CommandType(EnumSet<RoleType> acceptableRole) {
        this.acceptableRole = acceptableRole;
    }

    public EnumSet<RoleType> getAcceptableRole() {
        return acceptableRole;
    }
}