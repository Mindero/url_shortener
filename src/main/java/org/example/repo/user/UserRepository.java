package org.example.repo.user;

import org.example.exception.UserExistException;
import org.example.repo.user.UserDao.UserDao;
import org.example.service.object.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {
    void addUser(int id, int password) throws SQLException;
    Optional<UserDao> login(int id, int password) throws SQLException;
    boolean shortUrlExist(UserDao userDao, String shortUrl) throws SQLException;
    boolean userExist(int id) throws SQLException;
    void addUrl(UserDao userDao, String shortUrl) throws SQLException;
}
