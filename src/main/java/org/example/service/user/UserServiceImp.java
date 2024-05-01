package org.example.service.user;

import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.repo.url.UrlRepository;
import org.example.repo.user.UserDao.UserDao;
import org.example.repo.user.UserRepository;
import org.example.service.object.User;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

public class UserServiceImp implements UserService{
    static User user;
    private final UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void print() {
        if (user == null){
            System.out.println("null");
        }
        else System.out.println(user.id());
    }
}
