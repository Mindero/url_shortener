package org.example.repo.url.UrlDao;

public record UrlDao(String longUrl, String shortUrl) {
    public UrlDao (String longUrl, String shortUrl){
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }
}
