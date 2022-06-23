package by.grits.news.service.impl;

import by.grits.news.dao.UserDao;
import by.grits.news.dao.exception.DaoException;
import by.grits.news.dao.impl.UserDaoImpl;
import by.grits.news.entities.User;
import by.grits.news.entities.enums.RoleType;
import by.grits.news.service.UserService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.util.EmailValidator;
import by.grits.news.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static by.grits.news.command.RequestParameter.WRONG_DATA_MARKER;
import static by.grits.news.command.SessionAttribute.*;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User login(Map<String, String> userData) throws ServiceException {
        User user = null;
        String email = userData.get(USER_EMAIL_SESSION);
        String password = userData.get(PASSWORD_SESSION);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        if (!EmailValidator.validateEmailInput(email)) {
            return null;
        }
        try {
            String encodedPassword = passwordEncoder.encode(password);
            user = userDao.logIn(email, encodedPassword);
            if (user == null) {
                LOGGER.info("User: " + email + " was not found in database");
                userData.put(NOT_FOUND_SESSION, WRONG_DATA_MARKER);
            }
        } catch (DaoException e) {
            LOGGER.error("Try to authenticate user " + email + password + " was failed.", e);
            //throw new ServiceException("Try to authenticate user " + email + password + " was failed.", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Smth wrong with encoding password");
        }
        return user;
    }

    @Override
    public boolean signup(Map<String, String> userData) throws ServiceException {
        if (!EmailValidator.validateEmailInput(userData.get(USER_EMAIL_SESSION))) {
            return false;
        }
        String email = userData.get(USER_EMAIL_SESSION);
        String password = userData.get(PASSWORD_SESSION);

        try {
            if (userDao.findByEmail(email) != null) {
                userData.put(WRONG_EMAIL_EXISTS_SESSION, WRONG_DATA_MARKER);
                return false;
            }
            PasswordEncoder passwordEncoder = new PasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            User newUser = new User(email, encodedPassword, "", RoleType.USER, LocalDate.now());
            userDao.insert(newUser);
            return true;
        } catch (DaoException | NoSuchAlgorithmException e) {
            LOGGER.error("Could not create new account ", e);
            throw new ServiceException("Creating new account failed", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> allUsers;
        try {
            allUsers = userDao.findAll();
        } catch (DaoException e) {
            LOGGER.error("Failed getting al users", e);
            throw new ServiceException("Failed getting all users.", e);
        }
        return allUsers;
    }

    @Override
    public User findUserByEmail(String userEmail) throws ServiceException {
        User user = null;
        try {
            user = userDao.findByEmail(userEmail);
        } catch (DaoException e) {
            LOGGER.error("Try to find user by email " + userEmail + " was failed.", e);
            throw new ServiceException("Try to find user by id " + userEmail + " was failed.", e);
        }
        return user;
    }
}
