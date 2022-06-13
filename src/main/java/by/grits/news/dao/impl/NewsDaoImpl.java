package by.grits.news.dao.impl;

import by.grits.news.dao.NewsDao;
import by.grits.news.dao.connection.ConnectionPool;
import by.grits.news.entities.News;
import by.grits.news.dao.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    private static final Logger LOGGER = LogManager.getLogger(NewsDaoImpl.class);

    private static final String INSERT =
            "insert into news(title, summary, content, author, added_at) values(?,?,?,?,?)";
    private static final String UPDATE =
            "update news set title=?, summary=?, content=?, author=? where id=?";
    private static final String DELETE =
            "delete from news where id=?";
    private static final String GET_BY_ID =
            "select id, title, summary, content, author, added_at from news where id=?";
    private static final String GET_ALL =
            "select id, title, summary, content, author, added_at from news";

    private static NewsDaoImpl instance;

    private NewsDaoImpl() {
    }

    public static NewsDaoImpl getInstance() {
        if (instance == null) {
            instance = new NewsDaoImpl();
        }
        return instance;
    }

    @Override
    public void insert(News news) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getSummary());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setString(4, news.getAuthor());
            preparedStatement.setDate(5, Date.valueOf(news.getAddedAt()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while insert query: " + e.getMessage());
            throw new DaoException("Error while insert query: " + e.getMessage());
        }

    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error while delete query: " + e.getMessage());
            throw new DaoException("Error while insert query: " + e.getMessage());
        }

    }

    @Override
    public void update(News news) throws DaoException {


    }

    @Override
    public News findById(Integer id) throws DaoException {
        News news = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    news = new News(resultSet.getString("title"), resultSet.getString("summary"),
                            resultSet.getString("content"), resultSet.getString("author"));
                    news.setId(resultSet.getInt("id"));
                    news.setAddedAt(resultSet.getDate("added_at").toLocalDate());
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error while select query: " + e.getMessage());
            throw new DaoException("Error while insert query: " + e.getMessage());
        }
        return news;
    }

    @Override
    public List<News> findAll() throws DaoException {
        News news = null;
        List<News> allNews = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news = new News(resultSet.getString("title"), resultSet.getString("summary"),
                        resultSet.getString("content"), resultSet.getString("author"));
                news.setId(resultSet.getInt("id"));
                news.setAddedAt(resultSet.getDate("added_at").toLocalDate());
                allNews.add(news);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while select query: " + e.getMessage());
            throw new DaoException("Error while select query: " + e.getMessage());
        }
        return allNews;
    }
}
