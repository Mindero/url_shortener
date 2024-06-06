package org.example.service.url;

import jakarta.transaction.Transactional;
import org.example.kafka.UrlCountProducer;
import org.example.repo.entity.UrlEntity;
import org.example.repo.entity.UserEntity;
import org.example.repo.url.UrlRepository;
import org.example.service.object.Url;
import org.example.exception.URLisNotFind;
import org.example.service.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService{
    private final UrlRepository urlRepository;
    private final UrlCountProducer urlCountProducer;


    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, UrlCountProducer urlCountProducer){
        this.urlRepository = urlRepository;
        this.urlCountProducer = urlCountProducer;
    }

    @Override
    public String addUrl(Url longUrl, User user){
        String shortUrl = createShortUrl(longUrl.url());
        UserEntity userEntity = new UserEntity(user.id());
        UrlEntity urlEntity = new UrlEntity(shortUrl, longUrl.url(), userEntity, longUrl.onlyOnce());
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    @Override
    @Cacheable(cacheNames = "url", cacheManager = "RedisInMemoryCacheManager")
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        if (!urlRepository.existsByShorturl(shortUrl))
            throw new URLisNotFind();
        UrlEntity res = urlRepository.findById(shortUrl).get();
        // Строку можно прочитать ровно один раз. Удаляем её
        if (res.getOnlyonce()) urlCountProducer.sendMessages(shortUrl);
        return res.getLongurl();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "url", cacheManager = "RedisInMemoryCacheManager")
    public void deleteUrl(String shortUrl){
        urlRepository.deleteById(shortUrl);
    }

    @Override
    public List<String> getAllNotUpdatedFor(){
        return urlRepository.findAllByUpdatedDateBefore(Instant.now().minus(20, ChronoUnit.MINUTES));
    }
    private String createShortUrl(String longUrl){
        Random rnd = new Random();
        final int sizeShortUrl = 5;
        StringBuilder shortUrl = new StringBuilder();
        while(true) { // While shortUrl contains in database
            shortUrl.setLength(0);
            for (int i = 0; i < sizeShortUrl; ++i) {
                String alph = "0123456789qwertyuiopasdfghjklzxcvbnm";
                int random = rnd.nextInt(alph.length());
                shortUrl.append(alph.charAt(random));
            }
            if (!urlRepository.existsByShorturl(shortUrl.toString())) break;
        }
        return shortUrl.toString();
    }
}
