package org.example.service.user;

import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.repo.entity.UserEntity;
import org.example.repo.user.UserRepository;
import org.example.service.object.Url;
import org.example.service.object.User;
import org.example.service.url.UrlService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    static User user =null;
    private final UserRepository userRepository;
    private final UrlService urlService;
    public UserServiceImp(UserRepository userRepository, UrlService urlService ) {
        this.userRepository = userRepository;
        this.urlService = urlService;
    }
    @Override
    public void register(String login, String password) throws UserExistException{
        if (userRepository.existsByLogin(login))
            throw new UserExistException();
        UserEntity userEntity = new UserEntity(login, password);
        UserEntity savedUser = userRepository.saveAndFlush(userEntity);
        user = new User(savedUser.getId());
    }
    @Override
    public void login(String login, String password) throws UserPasswordIncorrect{
        Optional<UserEntity> loginUser = userRepository.findOptionalByLoginAndPassword(login, password);
        if (loginUser.isEmpty())
            throw new UserPasswordIncorrect();
        user = new User(loginUser.get().getId());
    }
    @Override
    public void logout() throws logoutException{
        if (user == null) throw new logoutException();
        user = null;
    }
    @Override
    public String addUrl(Url LongUrl) throws logoutException{
        if (user == null) throw new logoutException();
        return urlService.addUrl(LongUrl, user);
    }
    @Override
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        return urlService.getLongUrl(shortUrl);
    }
}
