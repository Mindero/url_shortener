package org.example.controller.dto;

public record UrlDto(String longUrl) {
    public UrlDto(String longUrl){
        this.longUrl = longUrl;
    }
}
