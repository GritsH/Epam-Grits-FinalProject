package by.grits.news.service;

import by.grits.news.dao.UserDao;
import by.grits.news.entities.User;
import by.grits.news.service.exception.ServiceException;

import java.util.Map;

public interface UserService {
    User login(Map<String, String> userData) throws ServiceException;

    boolean signup(Map<String, String> userData) throws ServiceException;

    User findUserByEmail(String userEmail) throws ServiceException;

    void init(UserDao userDao);
}
