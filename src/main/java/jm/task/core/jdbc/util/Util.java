package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private  static final String USERNAME = "root";
    private  static final String PASSWORD = "12019050";
    private  static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false\" + \"&serverTimezone=UTC";

    private static Driver driver;
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
