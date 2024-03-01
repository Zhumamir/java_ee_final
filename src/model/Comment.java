package model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String comment;
    private News news;
    private User author;
    private LocalDateTime post_date;

    public Comment() {}

    public Comment(Long id, String comment, News news, User author, LocalDateTime post_date) {
        this.id = id;
        this.comment = comment;
        this.news = news;
        this.author = author;
        this.post_date = post_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }
}
