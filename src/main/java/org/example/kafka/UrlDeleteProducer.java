package org.example.kafka;

import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UrlDeleteProducer {
    private final KafkaTemplate<String, DeletedUrlKafkaMsg> kafkaUrlDeleteTemplate;

    public UrlDeleteProducer(KafkaTemplate<String, DeletedUrlKafkaMsg> kafkaUrlDeleteTemplate) {
        this.kafkaUrlDeleteTemplate = kafkaUrlDeleteTemplate;
    }

    public void sendMessages(List<String> ids){
        for (String id : ids){
            System.out.println(id);
            kafkaUrlDeleteTemplate.send("url-delete-topic", new DeletedUrlKafkaMsg(id));
        }
    }
}
