package org.example.service.object;

public record Url(String url) {
    public Url(String url){
        this.url = url;
    }
}
