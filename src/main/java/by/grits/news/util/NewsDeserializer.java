package by.grits.news.util;

import by.grits.news.entities.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDeserializer {
    public static List<News> deserialize(ResultSet resultSet) throws SQLException {
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
}
