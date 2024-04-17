package org.example.controller;

import org.example.exception.URLisNotFind;
import org.example.service.object.Url;
import org.example.controller.dto.UrlDto;
import org.example.service.UrlService;
import java.util.Optional;
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    public String addShortUrl(UrlDto urlDto){
        return urlService.addUrl(new Url(urlDto.longUrl()));
    }
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        return urlService.getLongUrl(shortUrl);
    }
}
