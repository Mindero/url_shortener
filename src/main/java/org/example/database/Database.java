package org.example.database;

import org.example.repo.UrlRepositoryImp;

import javax.xml.crypto.Data;
import java.util.HashMap;

public class Database {
    /// shortUrl -> longUrl
    private final HashMap<String, String> shortUrls;
    // longUrl -> shortUrl
    private final HashMap<String, String> longUrls;
    private static volatile Database data;
    private Database(){
        shortUrls =new HashMap<>();
        longUrls = new HashMap<>();
    }
    public static Database getDatabase(){
        Database localData = data;
        if (localData == null){
            synchronized (UrlRepositoryImp.class){
                data = new Database();
                localData = data;
            }
        }
        return localData;
    }
    public void save(String shortUrl, String longUrl){
        shortUrls.put(shortUrl, longUrl);
        longUrls.put(longUrl, shortUrl);
    }
    public String getLongUrl(String shortUrl){
        return shortUrls.get(shortUrl);
    }
    public boolean contains(String shortUrl){
        return shortUrls.containsKey(shortUrl);
    }
    public String getShortUrl(String longUrl){
        return longUrls.get(longUrl);
    }


}
