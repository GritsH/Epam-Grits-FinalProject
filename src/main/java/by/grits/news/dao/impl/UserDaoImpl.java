package by.grits.news.dao.impl;

import by.grits.news.dao.UserDao;
import by.grits.news.dao.connection.ConnectionPool;
import by.grits.news.entities.User;
import by.grits.news.entities.enums.RoleType;
import by.grits.news.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final String INSERT =
            "insert into users(email_address, user_password, user_name, role_type, added_at) values(?,?,?,?,?)";
    private static final String DELETE =
            "delete from users where email_address=?";
    private static final String GET_BY_EMAIL =
            "select email_address,user_password, user_name, role_type, added_at from users where email_address=?";
    private static final String GET_ALL =
            "select email_address, user_name, user_password, role_type, added_at from users";
    private static final String LOGIN =
            "select email_address, user_password, user_name, role_type, added_at from users where email_address=? and user_password=?";

    private static UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public void insert(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setDate(5, Date.valueOf(user.getAddedAt()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error while insert query: " + e.getMessage());
        }
    }

    @Override
    public void delete(String userEmail) throws DaoException {


    }

    @Override
    public List<User> findAll() throws DaoException {
        User user;
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), RoleType.valueOf(resultSet.getString(4)),
                        resultSet.getDate(5).toLocalDate());
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while select query: " + e.getMessage());
        }
        return allUsers;
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL);
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(email, resultSet.getString("user_password"), resultSet.getString("user_name"),
                            RoleType.valueOf(resultSet.getString("role_type")),
                            resultSet.getDate("added_at").toLocalDate());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while select query: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User logIn(String email, String password) throws DaoException {
        User user;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                user = new User(resultSet.getString("email_address"), resultSet.getString("user_password"),
                        resultSet.getString("user_name"), RoleType.valueOf(resultSet.getString("role_type")),
                        resultSet.getDate("added_at").toLocalDate());
            }
        } catch (SQLException e) {
            throw new DaoException("Error while select query: " + e.getMessage());
        }
        return user;
    }
}