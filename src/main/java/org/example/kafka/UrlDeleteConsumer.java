package org.example.kafka;

import jakarta.transaction.Transactional;
import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.example.service.object.Url;
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
    @KafkaListener(topics="url-delete-topic", groupId = "url-delete", containerFactory = "kafkaDeleteUrlListenerContainerFactory")
    public void consume(DeletedUrlKafkaMsg message){
        System.out.println("Consume delete");
        if (message.shortUrl() != null){
            urlService.deleteUrl(message.shortUrl());
        }
    }
}
