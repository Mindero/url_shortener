package org.example.kafka;

import jakarta.transaction.Transactional;
import org.example.kafka.dto.CntUrlKafkaMsg;
import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.example.repo.entity.UrlEntity;
import org.example.service.url.UrlService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UrlCountConsumer {
    private final UrlService urlService;

    public UrlCountConsumer(UrlService urlService){
        this.urlService = urlService;
    }
    @Transactional
    @KafkaListener(topics="url-cnt-topic", groupId = "url-cnt",containerFactory = "kafkaCntUrlListenerContainerFactory")
    public void consume(CntUrlKafkaMsg message){
        if (message.shortUrl() != null){
            urlService.updateCnt(message.shortUrl());
        }
    }
}
