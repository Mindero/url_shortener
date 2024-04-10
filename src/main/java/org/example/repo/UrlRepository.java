package org.example.repo;

import org.example.repo.UrlDao.UrlDao;
import java.util.Optional;

public interface UrlRepository {
    String addUrl(UrlDao urlDao);

    Optional<String> getLongUrl(String shortUrl);
}
