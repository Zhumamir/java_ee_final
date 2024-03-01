package model;

import java.time.LocalDateTime;

public class News {
    private Long id;
    private LocalDateTime post_date;
    private Long categoryId;
    private String title;
    private String content;

    public News() {}

    public News(Long id, LocalDateTime post_date, Long categoryId, String title, String content) {
        this.id = id;
        this.post_date = post_date;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public News(Long categoryId, String title, String content) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
