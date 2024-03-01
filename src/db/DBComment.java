package db;

import model.Comment;
import model.News;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBComment {
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

    public static List<Comment> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT *, n.title, n.content, n.post_date AS newsPostedAt, u.email, u.full_name " +
                            "FROM COMMENTS c " +
                            "INNER JOIN NEWS n on n.id = c.news_id " +
                            "INNER JOIN USERS u on u.id = c.user_id " +
                            "WHERE c.news_id = ?"
            );
            statement.setLong(1, newsId);
            var result = statement.executeQuery();
            while (result.next()) {
                Comment comment = new Comment();
                comment.setId(result.getLong("id"));
                comment.setComment(result.getString("comment"));
                comment.setPost_date(result.getObject("post_date", LocalDateTime.class));

                User author = new User();
                author.setId(result.getLong("user_id"));
                author.setEmail(result.getString("email"));
                author.setFull_name(result.getString("full_name"));

                comment.setAuthor(author);

                News news = new News();
                news.setId(result.getLong("news_id"));
                news.setTitle(result.getString("title"));
                news.setContent(result.getString("content"));
                news.setPost_date(result.getObject("post_date", LocalDateTime.class));

                comment.setNews(news);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(String comment, Long authorId, Long newsId) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO COMMENTS (comment, news_id, user_id, post_date) " +
                            "VALUES (?, ?, ?, now())"
            );
            statement.setString(1, comment);
            statement.setLong(2, newsId);
            statement.setLong(3, authorId);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
