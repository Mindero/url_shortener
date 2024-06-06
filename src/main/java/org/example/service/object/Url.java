package org.example.service.object;

import org.apache.kafka.common.protocol.types.Field;

public record Url(String url, Boolean onlyOnce) {
    public Url(String url, Boolean onlyOnce){
        this.url = url;
        this.onlyOnce = onlyOnce;
    }
}
