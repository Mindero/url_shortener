package org.example.service;

import org.example.repo.UrlRepository;
import org.example.service.object.Url;
import org.example.repo.UrlDao.UrlDao;
import org.example.exception.URLisNotFind;

import java.util.Optional;

public class UrlServiceImp implements UrlService{
    private final UrlRepository urlRepository;

    public UrlServiceImp(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    public String addUrl(Url LongUrl){
        var UrlDao = new UrlDao(LongUrl.url());
        return urlRepository.addUrl(UrlDao);
    }

    public String getLongUrl(String shortUrl) throws URLisNotFind{
        Optional<String> url = urlRepository.getLongUrl(shortUrl);
        if (url.isEmpty()) throw new URLisNotFind();
        return url.get();
    }
}
