package org.example.kafka;

import org.example.kafka.dto.CntUrlKafkaMsg;
import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UrlCountProducer {
    private final KafkaTemplate<String, CntUrlKafkaMsg> kafkaCntUrlTemplate;

    public UrlCountProducer(KafkaTemplate<String, CntUrlKafkaMsg> kafkaCntUrlTemplate) {
        this.kafkaCntUrlTemplate = kafkaCntUrlTemplate;
    }

    public void sendMessages(String shortUrl){
        kafkaCntUrlTemplate.send("url-cnt-topic", new CntUrlKafkaMsg(shortUrl));
    }
}
