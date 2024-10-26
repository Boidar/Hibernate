package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Connection connection = Util.getConnection();
    public void createUsersTable() {
    String sql1 = """
            CREATE TABLE IF NOT EXISTS users (
                id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,\s
                name VARCHAR(255) NOT NULL,\s
                lastName VARCHAR(255) NOT NULL,\s
                age TINYINT UNSIGNED\s
            )""";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public void dropUsersTable() {
        String sql2 = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void saveUser(String name, String lastName, byte age) {
            String sql5 = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql5)) {
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,lastName);
                preparedStatement.setByte(3,age);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String quary = "Select * from users";
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql3 = "TRUNCATE users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql3)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
