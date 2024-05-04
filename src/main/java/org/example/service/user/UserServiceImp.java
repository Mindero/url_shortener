package org.example.service.user;

import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.repo.url.UrlRepository;
import org.example.repo.user.UserDao.UserDao;
import org.example.repo.user.UserRepository;
import org.example.service.object.Url;
import org.example.service.object.User;
import org.example.service.url.UrlService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class UserServiceImp implements UserService{
    static User user = null;
    private final UserRepository userRepository;
    private final UrlService urlService;
    public UserServiceImp(UserRepository userRepository, UrlService urlService ) {
        this.userRepository = userRepository;
        this.urlService = urlService;
    }
    public boolean register(String login, String password) throws UserExistException{
        int id = login.hashCode();
        int hash_password = password.hashCode();
        try {
            if (userRepository.userExist(id)) throw new UserExistException();
        }
        catch (SQLException ex){
            throw new RuntimeException("Error with userExistByLogin " + ex.getMessage());
        }
        try{
            userRepository.addUser(id, hash_password);
            user = new User(id);
        }
        catch (SQLException ex){
            throw new RuntimeException("Error with addUser " + ex.getMessage());
        }
        return true;
    }
    public boolean login(String login, String password) throws UserPasswordIncorrect{
        try {
            Optional<UserDao> userDao = userRepository.login(login.hashCode(), password.hashCode());
            if (userDao.isEmpty()) throw new UserPasswordIncorrect();
            else {
                user = new User(userDao.get().id());
                return true;
            }
        }
        catch (SQLException ex){
            throw new RuntimeException("Error with login" + ex.getMessage());
        }
    }
    public void logout() throws logoutException{
        if (user == null) throw new logoutException();
        user = null;
    }
    public void print() {
        if (user == null){
            System.out.println("null");
        }
        else System.out.println(user.id());
    }
    public String addUrl(Url LongUrl) throws logoutException{
        if (user == null) throw new logoutException();
        String shortUrl = urlService.addUrl(LongUrl);
        try{
            userRepository.addUrl(new UserDao(user.id()), shortUrl);
        }
        catch (SQLException ex)
        {
            throw new RuntimeException("Error with user add url" + ex.getMessage());
        }
        return shortUrl;
    }
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        return urlService.getLongUrl(shortUrl);
    }
}
