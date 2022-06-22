package by.grits.news.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class News implements Serializable, Comparable<News> {
    private static final long serialVersionUID = 123L;

    private Integer id;
    private String title;
    private String summary;
    private String content;
    private LocalDate addedAt;
    private String author;

    public News(String title, String summary, String content, String author) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(summary, news.summary) && Objects.equals(content, news.content) && Objects.equals(addedAt, news.addedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summary, content, addedAt);
    }

    @Override
    public String  toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }

    @Override
    public int compareTo(News news) {
        return getAddedAt().compareTo(news.getAddedAt());
    }
}
