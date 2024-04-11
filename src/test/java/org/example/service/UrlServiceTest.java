package org.example.service;
import org.example.exception.URLisNotFind;
import org.example.repo.UrlRepositoryImp;
import org.example.service.object.Url;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlServiceTest {
    private final UrlService urlService =new UrlServiceImp(new UrlRepositoryImp());

    @Test
    void testAddUrl() throws URLisNotFind{
        // given:
        Url url = new Url("abacaba");
        // when:
        String shortUrl = urlService.addUrl(url);
        String LongUrl = urlService.getLongUrl(shortUrl);
        // then:
        assertEquals(url.url(), LongUrl);
    }

}
