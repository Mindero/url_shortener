package org.example.controller;

import org.example.controller.dto.UrlRequestDto;
import org.example.controller.dto.UserDto;
import org.example.exception.URLisNotFind;
import org.example.exception.UserExistException;
import org.example.exception.UserPasswordIncorrect;
import org.example.exception.LogoutException;
import org.example.service.object.Url;
import org.example.controller.dto.UrlResponseDto;
import org.example.service.user.UserService;
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
    public void logout() throws LogoutException {
        userService.logout();
    }
    @PostMapping(path="/short", consumes = "application/json")
    public String addShortUrl(@RequestBody UrlRequestDto urlRequestDto) throws LogoutException {
        return userService.addUrl(new Url(urlRequestDto.url(), urlRequestDto.onlyOnce()));
    }
    @GetMapping("/{shortUrl}")
    public UrlResponseDto getLongUrl(@PathVariable("shortUrl") String shortUrl) throws URLisNotFind{

        return new UrlResponseDto(userService.getLongUrl(shortUrl));
    }
}
