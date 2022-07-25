package by.grits.news.service.impl;

import by.grits.news.dao.NewsDao;
import by.grits.news.dao.exception.DaoException;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static by.grits.news.command.SessionAttribute.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {
    @Mock
    private NewsDao newsDao;
    @Mock
    private News mockedNews;
    NewsService newsService;
    Map<String, String> newsData = new HashMap<>();
    @Captor
    private ArgumentCaptor<News> newsArgumentCaptor;

    @BeforeEach
    void setup() {
        newsService = NewsServiceImpl.getInstance();
        newsService.init(newsDao);

        newsData.put(NEWS_ID_TO_EDIT_SESSION, "1");
        newsData.put(NEWS_TITLE_SESSION, "title");
        newsData.put(NEWS_SUMMARY_SESSION, "summary");
        newsData.put(NEWS_CONTENT_SESSION, "content");
        newsData.put(NEWS_AUTHOR_SESSION, "author");
        newsData.put(NEWS_ADDED_AT_SESSION, "2022-01-01");
    }

    @DisplayName("should add news")
    @Test
    void addNews() throws ServiceException, DaoException {
        News expectedNews = new News(newsData.get(NEWS_TITLE_SESSION), newsData.get(NEWS_SUMMARY_SESSION),
                newsData.get(NEWS_CONTENT_SESSION), newsData.get(NEWS_AUTHOR_SESSION));
        expectedNews.setAddedAt(LocalDate.parse(newsData.get(NEWS_ADDED_AT_SESSION)));

        newsService.addNews(newsData);
        verify(newsDao).insert(newsArgumentCaptor.capture());
        verifyNoMoreInteractions(newsDao);

        assertEquals(expectedNews, newsArgumentCaptor.getValue());

    }


    @DisplayName("should delete news by id")
    @Test
    void deleteNewsById() throws ServiceException, DaoException {
        newsService.deleteNews(1);

        verify(newsDao).delete(1);
        verifyNoMoreInteractions(newsDao);
    }

    @DisplayName("should update news")
    @Test
    void updateNews() throws ServiceException, DaoException {

        News expectedNews = new News(newsData.get(NEWS_TITLE_SESSION), newsData.get(NEWS_SUMMARY_SESSION),
                newsData.get(NEWS_CONTENT_SESSION), newsData.get(NEWS_AUTHOR_SESSION));
        expectedNews.setAddedAt(LocalDate.parse(newsData.get(NEWS_ADDED_AT_SESSION)));
        expectedNews.setId(Integer.parseInt(newsData.get(NEWS_ID_TO_EDIT_SESSION)));

        newsData.put(NEWS_CONTENT_SESSION, "updated_content");

        newsService.updateNews(newsData);
        verify(newsDao).update(newsArgumentCaptor.capture());
        verifyNoMoreInteractions(newsDao);

        assertNotEquals(expectedNews, newsArgumentCaptor.getValue());
    }

    @DisplayName("should find all news")
    @Test
    void findAllNews() throws DaoException, ServiceException {
        List<News> newsDaoResponse = new ArrayList<>();
        newsDaoResponse.add(mockedNews);

        when(newsDao.findAll()).thenReturn(newsDaoResponse);

        List<News> result = newsService.findAllNews();

        assertEquals(mockedNews, result.iterator().next());
        assertEquals(1, result.size());

        verify(newsDao).findAll();
        verifyNoMoreInteractions(newsDao);
    }

    @DisplayName("should find news by id")
    @Test
    void findNewsById() throws DaoException, ServiceException {
        when(newsDao.findById(1)).thenReturn(mockedNews);

        News result = newsService.findNewsById(1);

        assertEquals(mockedNews, result);

        verify(newsDao).findById(1);
        verifyNoMoreInteractions(newsDao);
    }
}