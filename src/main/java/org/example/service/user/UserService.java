package org.example.service.user;

import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.service.object.Url;

public interface UserService {
    void register(String login, String password) throws UserExistException;
    void login(String login, String password) throws UserPasswordIncorrect;
    void logout() throws logoutException;
    String addUrl(Url LongUrl) throws logoutException;

    String getLongUrl(String shortUrl) throws URLisNotFind;
}
