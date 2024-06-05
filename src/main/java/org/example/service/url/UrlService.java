package org.example.service.url;

import org.example.service.object.Url;
import org.example.exception.URLisNotFind;
import org.example.service.object.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
public interface UrlService {
    String addUrl(Url LongUrl, User user);

    String getLongUrl(String shortUrl) throws URLisNotFind;

    void deleteUrl(String shortUrl);

    List<String> getAllNotUpdatedFor();

    void updateCnt(String shortUrl);
}
