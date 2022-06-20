package by.grits.news.service.impl;

import by.grits.news.dao.NewsDao;
import by.grits.news.dao.exception.DaoException;
import by.grits.news.dao.impl.NewsDaoImpl;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.grits.news.command.SessionAttribute.*;

public class NewsServiceImpl implements NewsService {
    private static final Logger LOGGER = LogManager.getLogger(NewsServiceImpl.class);

    private static NewsServiceImpl instance = new NewsServiceImpl();
    private NewsDao newsDao = NewsDaoImpl.getInstance();

    private NewsServiceImpl() {
    }

    public static NewsServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<News> findAllNews() throws ServiceException {
        List<News> allNews;
        try {
            allNews = newsDao.findAll();
        } catch (DaoException e) {
            LOGGER.error("Try to find all news was failed.", e);
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
            LOGGER.error("Try to find news by ID was failed.", e);
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
            LOGGER.error("Try to find all news by author was failed.", e);
            throw new ServiceException("Try to find all news by author was failed.", e);
        }
        return allNews;
    }

    @Override
    public boolean addNews(Map<String, String> newsData) throws ServiceException{
        boolean isAdded = false;
        String title = newsData.get(NEWS_TITLE_SESSION);
        String summary = newsData.get(NEWS_SUMMARY_SESSION);
        String content = newsData.get(NEWS_CONTENT_SESSION);
        String author = newsData.get(NEWS_AUTHOR_SESSION);
        String addedAt = newsData.get(NEWS_ADDED_AT_SESSION);

        try{
            News newsToAdd = new News(title, summary, content, author);
            newsToAdd.setAddedAt(LocalDate.parse(addedAt));
            isAdded = newsDao.insert(newsToAdd);
        }catch (DaoException e){
            LOGGER.error("Try to add news was failed.", e);
            throw new ServiceException("Try to add news was failed.", e);
        }
        return isAdded;
    }

    @Override
    public boolean updateNews(Map<String, String> newsData) throws ServiceException {
        Integer newsId = Integer.parseInt(newsData.get(NEWS_ID_SESSION));
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
            LOGGER.error("Try to update news was failed.", e);
            throw new ServiceException("Try to update news was failed.", e);
        }
        return true;
    }

    //todo pagination??
}
