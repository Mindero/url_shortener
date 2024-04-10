package org.example.service;

import org.example.service.object.Url;
import org.example.exception.URLisNotFind;
import java.util.Optional;
public interface UrlService {
    public String addUrl(Url LongUrl);

    public String getLongUrl(String shortUrl) throws URLisNotFind;
}
