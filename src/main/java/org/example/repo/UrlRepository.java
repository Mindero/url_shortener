package org.example.repo;

import org.example.repo.UrlDao.UrlDao;

import java.sql.SQLException;
import java.util.Optional;

public interface UrlRepository {
    String addUrl(UrlDao urlDao) throws SQLException;
    Optional<UrlDao> getLongUrl(String shortUrl) throws SQLException;
    boolean existShortUrl(String shortUrl) throws SQLException;
}
