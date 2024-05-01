package org.example.repo;

import org.example.repo.UrlDao.UrlDao;
import java.util.Optional;

public interface UrlRepository {
    String addUrl(UrlDao urlDao);
    boolean existLongUrl(UrlDao urlDao);
    boolean existShortUrl(UrlDao urlDao);
    String getShortUrl(UrlDao urlDao);
    Optional<String> getLongUrl(UrlDao urlDao);
}
