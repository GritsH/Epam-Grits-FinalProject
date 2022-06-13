package by.grits.news.dao;

import by.grits.news.entities.User;
import by.grits.news.dao.exceptions.DaoException;

import java.util.List;

public interface UserDao {
    void insert(User user) throws DaoException;

    void delete(String userEmail) throws DaoException;

    List<User> findAll() throws DaoException;

    User findByEmail(String email) throws DaoException;

    User signIn(String email, String password) throws DaoException;
}
