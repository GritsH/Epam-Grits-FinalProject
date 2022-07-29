package by.grits.news.entities;

import by.grits.news.entities.enums.RoleType;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String emailAddress;
    private String password;
    private RoleType role;
    private LocalDate addedAt;

    public User(String emailAddress, String password, RoleType role, LocalDate addedAt) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
        this.addedAt = addedAt;
    }

    public User() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAddress, user.emailAddress) && Objects.equals(password, user.password) && role == user.role && Objects.equals(addedAt, user.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, password, role, addedAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", addedAt=" + addedAt +
                '}';
    }
}
