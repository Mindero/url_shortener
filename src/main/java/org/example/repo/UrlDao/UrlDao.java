package org.example.repo.UrlDao;

import java.util.Optional;

public record UrlDao(String longUrl, String shortUrl) {
    public UrlDao (String longUrl, String shortUrl){
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }
}
