package org.example.service.url;

import org.example.service.object.Url;
import org.example.exception.URLisNotFind;
import java.util.Optional;
public interface UrlService {
    String addUrl(Url LongUrl);

    String getLongUrl(String shortUrl) throws URLisNotFind;
}
