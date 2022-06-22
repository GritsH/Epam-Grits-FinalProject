package by.grits.news.dao;

import by.grits.news.entities.News;
import by.grits.news.dao.exception.DaoException;

import java.util.List;

public interface NewsDao {
    boolean insert(News news) throws DaoException;

    void delete(Integer id) throws DaoException;

    void update(News news) throws DaoException;

    News findById(Integer id) throws DaoException;

    List<News> findByAuthor(String authorEmail) throws DaoException;

    List<News> findAll() throws DaoException;
}
