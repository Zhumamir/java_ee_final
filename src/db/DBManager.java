package db;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBManager {
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

    public static String createUser(User user, String rePassword) {
        User currentUser = findByEmail(user.getEmail());
        if (currentUser != null) {
            return "errorUsername";
        }

        if (!Objects.equals(user.getPassword(), rePassword)) {
            return "errorPasswords";
        }

        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO USERS(email, password, full_name, role_id) " +
                            "VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFull_name());
            statement.setInt(4, user.getRole());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static User findByEmail(String email) {
        User user = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM USERS " +
                            "WHERE email = ?"
            );
            statement.setString(1, email);
            var result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setEmail(email);
                user.setPassword(result.getString("password"));
                user.setFull_name(result.getString("full_name"));
                user.setRole(result.getInt("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User auth(String email, String password) {
        User user = findByEmail(email);
        if (user == null) {
            return null;
        }

        if (!Objects.equals(user.getPassword(), password)) {
            return null;
        }

        return user;
    }
}
