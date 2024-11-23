package wzorce.creational.singleton.solution2;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
class DatabaseConnectionManager {
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String username = "user";
    private String password = "password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}