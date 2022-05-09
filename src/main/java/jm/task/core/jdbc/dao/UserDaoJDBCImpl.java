package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connect;
import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    private String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS users (
            	user_id INT AUTO_INCREMENT PRIMARY KEY,
            	user_name VARCHAR(255) NOT NULL,
            	last_name VARCHAR(255) NOT NULL,
            	age INT
            )""";

    private final String DROP_TABLE = "DROP TABLE users";
    private final String SAVE_USER = "INSERT INTO users (user_name, last_name, age) values (?,?,?) ";
    private final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";
    private final String CLEAR_TABLE = "DELETE FROM users";
    private final String GET_ALL_USERS = "SELECT * FROM users";


    public UserDaoJDBCImpl() {
        connect();
    }

    public void createUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(DROP_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = getConnection().prepareStatement(SAVE_USER)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = getConnection().prepareStatement(DELETE_USER)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {

            try (ResultSet rs = statement.executeQuery(GET_ALL_USERS)) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("user_id"));
                    user.setAge((byte) rs.getInt("age"));
                    user.setName(rs.getString("user_name"));
                    user.setLastName(rs.getString("last_name"));
                    list.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (PreparedStatement ps = getConnection().prepareStatement(CLEAR_TABLE)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
