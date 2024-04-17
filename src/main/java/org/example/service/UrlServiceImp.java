package org.example.service;

import org.example.repo.UrlRepository;
import org.example.service.object.Url;
import org.example.repo.UrlDao.UrlDao;
import org.example.exception.URLisNotFind;

import java.util.Optional;
import java.util.Random;

public class UrlServiceImp implements UrlService{
    private final UrlRepository urlRepository;

    private final String alph = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static final int sizeShortUrl = 7;

    public UrlServiceImp(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    public String addUrl(Url longUrl){
        var urlDao = new UrlDao(longUrl.url(), null);
        if (urlRepository.existLongUrl(urlDao)) {
            return urlRepository.getShortUrl(urlDao);
        }
        else {
            String shortUrl = createShortUrl(longUrl.url());
            urlDao = new UrlDao(longUrl.url(), shortUrl);
            return urlRepository.addUrl(urlDao);
        }
    }

    public String getLongUrl(String shortUrl) throws URLisNotFind{
        var urlDao = new UrlDao(null, shortUrl);
        Optional<String> longUrl = urlRepository.getLongUrl(urlDao);
        if (longUrl.isEmpty()) throw new URLisNotFind();
        return longUrl.get();
    }
    private String createShortUrl(String longUrl){
        Random rnd = new Random();
        StringBuilder shortUrl = new StringBuilder();
        while(true) { // While shortUrl contains in database
            shortUrl.setLength(0);
            for (int i = 0; i < sizeShortUrl; ++i) {
                int random = rnd.nextInt(alph.length());
                shortUrl.append(alph.charAt(random));
            }
            var urlDao = new UrlDao(longUrl, shortUrl.toString());
            if (!urlRepository.existShortUrl(urlDao)) break;
        }
        return shortUrl.toString();
    }
}
