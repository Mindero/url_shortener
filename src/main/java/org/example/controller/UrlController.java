package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.service.object.Url;
import org.example.controller.dto.UrlDto;
import org.example.service.url.UrlService;
import org.example.service.user.UserService;

public class UrlController {
    private final UserService userService;
    public UrlController(UserService userService) {
        this.userService = userService;
    }
    public boolean login(UserDto userDto) throws UserPasswordIncorrect {
        return userService.login(userDto.login(), userDto.password());
    }
    public boolean register(UserDto userDto) throws UserExistException {
        return userService.register(userDto.login(), userDto.password());
    }
    public void logout() throws logoutException {
        userService.logout();
    }
    public void print(){
        userService.print();
    }
    public String addShortUrl(UrlDto urlDto) throws logoutException{
        return userService.addUrl(new Url(urlDto.longUrl()));
    }
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        return userService.getLongUrl(shortUrl);
    }
}
