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
    public void addUser(int id, String login, String password) throws SQLException {
        int hash_password = password.hashCode();
        Connection connection = jdbcUtils.getConnection();
        String query = "INSERT INTO users (id, login, password) " +
                "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement =connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, login);
        preparedStatement.setInt(3, hash_password);
        preparedStatement.execute();
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
    public boolean userExistByLogin(String login) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        String query = "SELECT id FROM users WHERE login=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        return false;
    }
}
