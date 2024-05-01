package org.example.repo.user;

import org.example.exception.UserExistException;
import org.example.jdbc.jdbcUtils;
import org.example.repo.url.UrlDao.UrlDao;
import org.example.repo.user.UserDao.UserDao;

import java.security.spec.ECParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImp implements UserRepository {
    public void addUser(int id, int password) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        String query = "INSERT INTO users (id, password) " +
                "VALUES (?, ?)";
        PreparedStatement preparedStatement =connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, password);
        preparedStatement.execute();
    }
    public Optional<UserDao> login(int id, int password) throws SQLException{
        Connection connection = jdbcUtils.getConnection();
        String query = "SELECT id, password FROM users WHERE id=? and password=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            UserDao userDao = new UserDao(resultSet.getInt("id"));
            return Optional.of(userDao);
        }
        return Optional.empty();
    }
    public boolean shortUrlExist(UserDao userDao, String shortUrl) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        String query = "SELECT * FROM usersUrls WHERE id = ? AND shortUrl = ?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setInt(1, userDao.id());
        preparedStatement.setString(2, shortUrl);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        return false;
    }
    public boolean userExist(int id) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        String query = "SELECT id FROM users WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        return false;
    }
}
