package by.grits.news.dao.impl;

import by.grits.news.dao.UserDao;
import by.grits.news.dao.connection.ConnectionPool;
import by.grits.news.entities.User;
import by.grits.news.entities.enums.RoleType;
import by.grits.news.dao.exception.DaoException;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String INSERT =
            "insert into users(email_address, user_password, role_type, added_at) values(?,?,?,?)";
    private static final String GET_BY_EMAIL =
            "select email_address,user_password, role_type, added_at from users where email_address=?";
    private static final String LOGIN =
            "select email_address, user_password, role_type, added_at from users where email_address=? and user_password=?";

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
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setDate(4, Date.valueOf(user.getAddedAt()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Error while insert query: " + e.getMessage());
        }
    }


    @Override
    public User findByEmail(String email) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL);
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(email, resultSet.getString("user_password"),
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
                        RoleType.valueOf(resultSet.getString("role_type")),
                        resultSet.getDate("added_at").toLocalDate());
            }
        } catch (SQLException e) {
            throw new DaoException("Error while select query: " + e.getMessage());
        }
        return user;
    }
}