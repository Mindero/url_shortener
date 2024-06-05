package org.example.kafka;

import org.example.kafka.dto.CntUrlKafkaMsg;
import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.example.repo.entity.UrlEntity;
import org.example.service.url.UrlService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UrlCountConsumer {
    private final UrlService urlService;

    public UrlCountConsumer(UrlService urlService){
        this.urlService = urlService;
    }
    @KafkaListener(topics="url-cnt-topic", containerFactory = "kafkaCntUrlListenerContainerFactory")
    public void consume(CntUrlKafkaMsg message){
        if (message.shortUrl() != null){
            urlService.updateCnt(message.shortUrl());
        }
    }
}
