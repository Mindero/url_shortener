package org.example.repo.user;

import org.example.exception.UserExistException;
import org.example.repo.user.UserDao.UserDao;

import java.sql.SQLException;

public interface UserRepository {
    void addUser(int id, String login, String password) throws SQLException;
    boolean shortUrlExist(UserDao userDao, String shortUrl) throws SQLException;
    boolean userExistByLogin(String login) throws SQLException;
}
