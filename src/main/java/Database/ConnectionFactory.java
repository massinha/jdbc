package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        final String HOST = "jdbc:mysql://localhost:3306/";
        final String DATABASE_NAME = "movies";
        final String USERNAME = "root";
        final String PASSWORD = "admin";
        final String TIMEZONE = "?useTimezone=true&serverTimezone=UTC";

        Connection connection = DriverManager.getConnection(HOST + DATABASE_NAME + TIMEZONE, USERNAME, PASSWORD);

        System.out.println("Conexao: " + connection != null);

        return connection;
    }

}
