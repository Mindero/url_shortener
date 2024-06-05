package org.example.service.url;

import org.example.repo.entity.UrlEntity;
import org.example.repo.entity.UserEntity;
import org.example.repo.url.UrlRepository;
import org.example.repo.user.UserRepository;
import org.example.service.object.Url;
import org.example.exception.URLisNotFind;
import org.example.service.object.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService{
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository, UserRepository userRepository){
        this.urlRepository = urlRepository;
    }
    @Override
    public String addUrl(Url longUrl, User user){
        String shortUrl = createShortUrl(longUrl.url());
        UserEntity userEntity = new UserEntity(user.id());
        UrlEntity urlEntity = new UrlEntity(shortUrl, longUrl.url(), userEntity);
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    @Override
    @Cacheable(cacheNames = "url", cacheManager = "RedisInMemoryCacheManager")
    public String getLongUrl(String shortUrl) throws URLisNotFind{
        if (!urlRepository.existsByShorturl(shortUrl))
            throw new URLisNotFind();
        var res = urlRepository.findById(shortUrl);
        return res.get().getLongurl();
    }

    @Override
    @CacheEvict(cacheNames = "url", cacheManager = "RedisInMemoryCacheManager")
    public void deleteUrl(String shortUrl){
        urlRepository.deleteById(shortUrl);
    }

    @Override
    public List<String> getStrangeThing(){
        return urlRepository.getStrangeThing();
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
