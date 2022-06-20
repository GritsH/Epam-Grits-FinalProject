package by.grits.news.service;

import by.grits.news.entities.News;
import by.grits.news.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface NewsService {
    List<News> findAllNews() throws ServiceException;
    News findNewsById(int id) throws ServiceException;
    List<News> findNewsByAuthor(String authorEmail) throws ServiceException;
    //todo deleteNews
    boolean addNews(Map<String, String> newsData) throws ServiceException;
    boolean updateNews(Map<String, String> newsData) throws ServiceException;
}
