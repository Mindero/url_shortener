package org.example.service.url;

import org.example.repo.url.UrlRepository;
import org.example.service.object.Url;
import org.example.repo.url.UrlDao.UrlDao;
import org.example.exception.URLisNotFind;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImp implements UrlService{
    private final UrlRepository urlRepository;

    private final String alph = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static final int sizeShortUrl = 5;

    public UrlServiceImp(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    @Override
    public String addUrl(Url longUrl){
        String shortUrl = createShortUrl(longUrl.url());
        var urlDao = new UrlDao(longUrl.url(), shortUrl);
        try{
            urlRepository.addUrl(urlDao);
        }
        catch (SQLException ex){
            //throw new RuntimeException("Error with adding new url", ex);
        }
        return shortUrl;
    }

    @Override
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        Optional<UrlDao> longUrl = Optional.empty();
        try{
            longUrl = urlRepository.getLongUrl(shortUrl);
        }
        catch(SQLException ex){
            //throw  new RuntimeException("Error with get long url ", ex);
        }
        if (longUrl.isEmpty()) throw new URLisNotFind();
        return longUrl.get().longUrl();
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
            try{
                boolean exist = urlRepository.existShortUrl(shortUrl.toString());
                if (!exist) break;
            }
            catch (SQLException ex){
                //throw new RuntimeException("Error with exist short url ", ex);
            }
        }
        return shortUrl.toString();
    }
}
