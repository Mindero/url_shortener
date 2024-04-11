package org.example.repo;

import org.example.repo.UrlDao.UrlDao;
import org.example.database.Database;

import javax.xml.crypto.Data;
import java.util.Optional;
import java.util.Random;

public class UrlRepositoryImp implements UrlRepository {
    private static final Database data = Database.getDatabase();
    private static final int sizeShortUrl = 7;
    private final String alph = "0123456789qwertyuiopasdfghjklzxcvbnm";
    public String addUrl(UrlDao urlDao){
        // TODO: Добваить проверку, что shortUrl - действительно ссылка.
        if (data.containsLongUrl(urlDao.Url()))
        {
            return data.getShortUrl(urlDao.Url());
        }
        String shortUrl = createShortUrl(urlDao.Url());
        data.save(shortUrl, urlDao.Url());
        return shortUrl;
    }
    public Optional<String> getLongUrl(String shortUrl){
        if (!data.containsShortUrl(shortUrl)) return Optional.empty();
        return Optional.of(data.getLongUrl(shortUrl));
    }

    private String createShortUrl(String longUrl){
        Random rnd = new Random();
        StringBuilder shortUrl = new StringBuilder();
        while(true) { // While shortUrls contains in database
            shortUrl.setLength(0);
            for (int i = 0; i < sizeShortUrl; ++i) {
                int random = rnd.nextInt(alph.length());
                shortUrl.append(alph.charAt(random));
            }
            if (!data.containsShortUrl(shortUrl.toString())) break;
        }
        return shortUrl.toString();
    }
}
