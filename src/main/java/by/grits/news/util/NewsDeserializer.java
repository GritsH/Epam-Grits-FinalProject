package by.grits.news.util;

import by.grits.news.command.RequestParameter;
import by.grits.news.command.exception.CommandException;
import by.grits.news.dao.impl.NewsDaoImpl;
import by.grits.news.entities.News;
import by.grits.news.service.NewsService;
import by.grits.news.service.exception.ServiceException;
import by.grits.news.service.impl.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDeserializer {
    public static List<News> deserializeAllNews(ResultSet resultSet) throws SQLException {
        List<News> allNews = new ArrayList<>();
        while (resultSet.next()) {
            News news = new News(resultSet.getString("title"), resultSet.getString("summary"),
                    resultSet.getString("content"), resultSet.getString("author"));
            news.setId(resultSet.getInt("id"));
            news.setAddedAt(resultSet.getDate("added_at").toLocalDate());
            allNews.add(news);
        }
        return allNews;
    }

    public static News deserializeNews(HttpServletRequest request) throws CommandException {
        String id = request.getParameter(RequestParameter.NEWS_ID);
        NewsService newsService = NewsServiceImpl.getInstance();
        newsService.init(NewsDaoImpl.getInstance());
        News foundNews;
        try {
            foundNews = newsService.findNewsById(Integer.parseInt(id));
        } catch (ServiceException e) {
            throw new CommandException("Could not retrieve news " + e);
        }
        return foundNews;
    }
}
