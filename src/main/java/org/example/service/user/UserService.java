package org.example.service.user;

import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.service.object.Url;

public interface UserService {
    boolean register(String login, String password) throws UserExistException;
    boolean login(String login, String password) throws UserPasswordIncorrect;
    void logout() throws logoutException;
    void print();
    String addUrl(Url LongUrl);

    String getLongUrl(String shortUrl) throws URLisNotFind;
}
