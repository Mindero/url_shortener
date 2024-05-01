package org.example.controller.dto;

public record UserDto(String login, String password) {
    public UserDto(String login, String password){
        this.login = login;
        this.password =password;
    }
}
