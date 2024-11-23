package wzorce.creational.singleton.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnectionManager {

    //Zmiana wartości instance przez jeden wątek jest natychmiast widoczna dla innych wątków
    private static volatile DatabaseConnectionManager instance;

    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String username = "user";
    private final String password = "password";

    private DatabaseConnectionManager() {
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
        // Inicjalizacja, jeśli konieczna
    }

    public static DatabaseConnectionManager getInstance() {
        DatabaseConnectionManager result = instance;
        // Sprawdzenie, czy instancja Singleton jest zainicjalizowana.
        // Jeśli tak, możemy zwrócić instancję.
        if (result == null) {
            // Nie jest zainicjalizowana, ale nie możemy być pewni, ponieważ inny wątek mógł ją
            // zainicjalizować w międzyczasie.
            // Aby mieć pewność, musimy zablokować obiekt, aby uzyskać wyłączność.
            synchronized (DatabaseConnectionManager.class) {
                // Ponowne przypisanie instancji do zmiennej lokalnej w celu sprawdzenia, czy została zainicjalizowana przez inny wątek podczas blokady.
                result = instance;
                if (result == null) {
                    // Instancja nadal nie jest zainicjalizowana, więc możemy bezpiecznie
                    // (żaden inny wątek nie może wejść do tego obszaru)
                    // stworzyć instancję i przypisać ją jako naszą instancję Singleton.
                    result = new DatabaseConnectionManager();
                    instance = result;
                }
            }
        }
        return result;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
