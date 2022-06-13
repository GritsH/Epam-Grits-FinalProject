package by.grits.news.entities;

import by.grits.news.entities.enums.RoleType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class User  implements Serializable {
    private static final long serialVersionUID = 456L;

    private String emailAddress;
    private String name;
    private String password;
    private RoleType role;
    private LocalDate addedAt;

    public User(String emailAddress, String password, String name, RoleType role, LocalDate addedAt) {
        this.emailAddress = emailAddress;
        this.name = name;
        this.password = password;
        this.role = role;
        this.addedAt = addedAt;
    }

    public User(RoleType role) {
        this.role = role;
    }

    public User() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRole(RoleType role) {
        this.role = role;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAddress, user.emailAddress) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && role == user.role && Objects.equals(addedAt, user.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, name, password, role, addedAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", addedAt=" + addedAt +
                '}';
    }
}
