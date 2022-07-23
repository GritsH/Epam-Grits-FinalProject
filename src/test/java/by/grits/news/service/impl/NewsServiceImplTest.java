package by.grits.news.service.impl;

import by.grits.news.dao.NewsDao;
import by.grits.news.dao.exception.DaoException;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {
    @Mock private NewsDao newsDao;
    @Mock private News mockedNews;
    NewsService newsService;

    @BeforeEach
    void setup() throws ServiceException {
        newsService = NewsServiceImpl.getInstance();
        newsService.init(newsDao);
    }

    @Test
    void deleteNewsById() throws ServiceException, DaoException {
        newsService.deleteNews(1);

        verify(newsDao).delete(1);
        verifyNoMoreInteractions(newsDao);
    }

    @Test
    void getAllNews() throws DaoException, ServiceException {
        List<News> newsDaoResponse = new ArrayList<>();
        newsDaoResponse.add(mockedNews);

        when(newsDao.findAll()).thenReturn(newsDaoResponse);

        Collection<News> result = newsService.findAllNews();

        assertEquals(mockedNews, result.iterator().next());
        assertEquals(1, result.size());

        verify(newsDao).findAll();
        verifyNoMoreInteractions(newsDao);
    }


}