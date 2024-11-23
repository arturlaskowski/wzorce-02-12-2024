package wzorce.creational.singleton.problem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnectionManager {

    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String username = "user";
    private final String password = "password";

    public DatabaseConnectionManager() {
        // Inicjalizacja, jeśli konieczna
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}