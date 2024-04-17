package org.example.repo;

import org.example.repo.UrlDao.UrlDao;
import org.example.database.Database;

import javax.xml.crypto.Data;
import java.util.Optional;
import java.util.Random;

public class UrlRepositoryImp implements UrlRepository {
    private static final Database data = Database.getDatabase();

    public String addUrl(UrlDao urlDao){
        // TODO: Добваить проверку, что shortUrl - действительно ссылка.
        data.save(urlDao.longUrl(), urlDao.shortUrl());
        return urlDao.shortUrl();
    }
    public boolean existLongUrl(UrlDao urlDao){
        return data.containsLongUrl(urlDao.longUrl());
    }
    public boolean existShortUrl(UrlDao urlDao){
        return data.containsShortUrl(urlDao.shortUrl());
    }

    public Optional<String> getLongUrl(UrlDao urlDao){
        if (!data.containsShortUrl(urlDao.shortUrl())) return Optional.empty();
        return Optional.of(data.getLongUrl(urlDao.shortUrl()));
    }
    public String getShortUrl(UrlDao urlDao){
        return data.getShortUrl(urlDao.longUrl());
    }


}
