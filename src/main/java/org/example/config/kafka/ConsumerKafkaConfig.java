package org.example.config.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.kafka.UrlDeleteConsumer;
import org.example.kafka.dto.DeletedUrlKafkaMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerKafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    private static final String GROUP_ID = "url-group";

    @Bean
    public ConsumerFactory<String, DeletedUrlKafkaMsg> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                GROUP_ID);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(DeletedUrlKafkaMsg.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeletedUrlKafkaMsg> kafkaDeleteUrlListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeletedUrlKafkaMsg> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
