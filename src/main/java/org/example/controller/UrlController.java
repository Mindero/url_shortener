package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.logoutException;
import org.example.service.object.Url;
import org.example.controller.dto.UrlDto;
import org.example.service.object.User;
import org.example.service.url.UrlService;
import org.example.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url_shortener")
public class UrlController {
    private final UserService userService;
    public UrlController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(path="/login", consumes = "application/json")
    public void login(@RequestBody UserDto userDto) throws UserPasswordIncorrect {
        userService.login(userDto.login(), userDto.password());
    }
    @PostMapping(path="/register", consumes = "application/json")
    public void register(@RequestBody UserDto userDto) throws UserExistException {
        userService.register(userDto.login(), userDto.password());
    }
    @PostMapping(path="/logout")
    public void logout() throws logoutException {
        userService.logout();
    }
    @PostMapping(path="/short", consumes = "application/json")
    public String addShortUrl(@RequestBody UrlDto urlDto) throws logoutException{
        return userService.addUrl(new Url(urlDto.url()));
    }
    @GetMapping("/{shortUrl}")
    public UrlDto getLongUrl(@PathVariable("shortUrl") String shortUrl) throws URLisNotFind{
        return new UrlDto(userService.getLongUrl(shortUrl));
    }
}
