package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        Connection connection = util.getConnection();
        try {
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS userstable " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)");

            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = util.getConnection();
        try {
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS userstable");

            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = util.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement pr = connection.prepareStatement
                    ("INSERT INTO userstable (name, lastName, age) VALUES(?, ?, ?)");
            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setByte(3, age);
            pr.executeUpdate();

            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = util.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement pr = connection.prepareStatement("DELETE FROM userstable WHERE id=?");
            pr.setLong(1, id);
            pr.executeUpdate();

            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = util.getConnection();

        try {
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM userstable";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = util.getConnection();
        try {
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE userstable");

            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
