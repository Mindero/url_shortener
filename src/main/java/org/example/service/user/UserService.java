package org.example.service.user;

import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.LogoutException;
import org.example.service.object.Url;

public interface UserService {
    void register(String login, String password) throws UserExistException;
    void login(String login, String password) throws UserPasswordIncorrect;
    void logout() throws LogoutException;
    String addUrl(Url LongUrl) throws LogoutException;

    String getLongUrl(String shortUrl) throws URLisNotFind;
}
