package by.grits.news.service.impl;

import by.grits.news.dao.NewsDao;
import by.grits.news.dao.exception.DaoException;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static by.grits.news.command.SessionAttribute.*;

public class NewsServiceImpl implements NewsService {

    private static NewsServiceImpl instance;
    private static NewsDao newsDao;

    private NewsServiceImpl() {
    }

    public static NewsServiceImpl getInstance() {
        if (instance == null) {
            instance = new NewsServiceImpl();
        }
        return instance;
    }

    @Override
    public void init(NewsDao newsDao1) throws ServiceException {
        newsDao = newsDao1;
    }

    @Override
    public List<News> findAllNews() throws ServiceException {
        List<News> allNews;
        try {
            allNews = newsDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Try to find all news was failed.", e);
        }
        return allNews;
    }

    @Override
    public News findNewsById(int id) throws ServiceException {
        News selectedNews;
        try {
            selectedNews = newsDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Try to find news by ID was failed.", e);
        }
        return selectedNews;
    }

    @Override
    public List<News> findNewsByAuthor(String authorEmail) throws ServiceException {
        List<News> allNews;
        try {
            allNews = newsDao.findByAuthor(authorEmail);
        } catch (DaoException e) {
            throw new ServiceException("Try to find all news by author was failed.", e);
        }
        return allNews;
    }

    @Override
    public void addNews(Map<String, String> newsData) throws ServiceException {
        String title = newsData.get(NEWS_TITLE_SESSION);
        String summary = newsData.get(NEWS_SUMMARY_SESSION);
        String content = newsData.get(NEWS_CONTENT_SESSION);
        String author = newsData.get(NEWS_AUTHOR_SESSION);
        String addedAt = newsData.get(NEWS_ADDED_AT_SESSION);

        try {
            News newsToAdd = new News(title, summary, content, author);
            newsToAdd.setAddedAt(LocalDate.parse(addedAt));
            newsDao.insert(newsToAdd);
        } catch (DaoException e) {
            throw new ServiceException("Try to add news was failed.", e);
        }
    }

    @Override
    public void updateNews(Map<String, String> newsData) throws ServiceException {
        Integer newsId = Integer.parseInt(newsData.get(NEWS_ID_TO_EDIT_SESSION));
        String newsTitle = newsData.get(NEWS_TITLE_SESSION);
        String newsSummary = newsData.get(NEWS_SUMMARY_SESSION);
        String newsContent = newsData.get(NEWS_CONTENT_SESSION);
        String newsAuthor = newsData.get(NEWS_AUTHOR_SESSION);
        String newsAddedAt = newsData.get(NEWS_ADDED_AT_SESSION);

        try {
            News toUpdateNews = new News(newsTitle, newsSummary, newsContent, newsAuthor);
            toUpdateNews.setId(newsId);
            toUpdateNews.setAddedAt(LocalDate.parse(newsAddedAt));
            newsDao.update(toUpdateNews);
        } catch (DaoException e) {
            throw new ServiceException("Try to update news was failed.", e);
        }
    }

    @Override
    public void deleteNews(Integer newsId) throws ServiceException {
        try {
            newsDao.delete(newsId);
        } catch (DaoException e) {
            throw new ServiceException("Try to delete news was failed.", e);
        }
    }
}
