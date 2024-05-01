package org.example.service;

import org.example.exception.URLisNotFind;
import org.example.repo.url.UrlRepositoryImp;
import org.example.service.object.Url;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class UrlServiceTest {
    private final UrlService urlService =new UrlServiceImp(new UrlRepositoryImp());

    @Test
    void testAddUrl(){
        // given:
        Url url = new Url("abacaba");
        // when:
        try {
            String shortUrl = urlService.addUrl(url);
            String LongUrl = urlService.getLongUrl(shortUrl);
            // then:
            assertEquals(url.url(), LongUrl);
        }
        // then:
        catch (URLisNotFind msg){
            assert(false);
        }
    }
    @Test
    void testExceptionGetUrl(){
        URLisNotFind thrown = assertThrows(URLisNotFind.class, ()->{
            // given:
            String shortUrl = "someShortUrl";
            Url url = new Url("abacaba");
            // when:
            String LongUrl = urlService.getLongUrl(url.url());
            // then:
            assertEquals(shortUrl, LongUrl);
        });
        assertEquals("Url is not find", thrown.getMessage());
    }
    @Test
    void addGetUrl(){
        // given:
        Url url = new Url("abacaba");
        try {
            String shortUrl = urlService.addUrl(url);
            // when:
            String LongUrl = urlService.getLongUrl(shortUrl);
            // then:
            assertEquals(url.url(), LongUrl);
        }
        //then:
        catch (URLisNotFind msg){
            assert(false);
        }

    }

}
