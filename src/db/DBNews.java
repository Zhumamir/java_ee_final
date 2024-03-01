package db;

import model.News;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBNews {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/TO2024G1?currentSchema=final",
                    "postgres",
                    "postgres"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<News> getNews() {
        var newsMore = new ArrayList<News>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.content, n.post_date " +
                            "FROM news n " +
                            "ORDER BY n.post_date DESC"
            );
            var result = statement.executeQuery();
            while (result.next()) {
                News news = new News();
                news.setId(result.getLong("id"));
                news.setTitle(result.getString("title"));
                news.setContent(result.getString("content"));
                news.setPost_date(result.getObject("post_date", LocalDateTime.class));

                newsMore.add(news);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsMore;
    }

    public static void createNews(News news) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO NEWS(title, content, category_id, post_date) " +
                            "VALUES(?, ?, ?, now())"
            );
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getCategoryId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static News getNewsById(Long id) {
        News news = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.content, n.post_date " +
                            "FROM NEWS n " +
                            "WHERE n.id = ?"
            );
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                news = new News();
                news.setId(result.getLong("id"));
                news.setTitle(result.getString("title"));
                news.setContent(result.getString("content"));
                news.setPost_date(result.getObject("post_date", LocalDateTime.class));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }
}
