package by.grits.news.service;

import by.grits.news.dao.NewsDao;
import by.grits.news.entities.News;
import by.grits.news.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface NewsService {
    List<News> findAllNews() throws ServiceException;

    News findNewsById(int id) throws ServiceException;


    void addNews(Map<String, String> newsData) throws ServiceException;

    void updateNews(Map<String, String> newsData) throws ServiceException;

    void deleteNews(Integer newsId) throws ServiceException;
    void init(NewsDao newsDao);
}
