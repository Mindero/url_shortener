package org.example.kafka;

import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.example.service.url.UrlService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UrlDeleteConsumer {
    private final UrlService urlService;

    public UrlDeleteConsumer(UrlService urlService){
        this.urlService = urlService;
    }
    @KafkaListener(topics="url-delete-topic", containerFactory = "kafkaDeleteUrlListenerContainerFactory")
    public void consume(DeletedUrlKafkaMsg message){
        System.out.println("Consume delete");
        if (message.shortUrl() != null){
            urlService.deleteUrl(message.shortUrl());
        }
    }
}
