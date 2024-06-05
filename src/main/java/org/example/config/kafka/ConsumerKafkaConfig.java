package org.example.config.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.kafka.UrlDeleteConsumer;
import org.example.kafka.dto.CntUrlKafkaMsg;
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


    @Bean
    public ConsumerFactory<String, DeletedUrlKafkaMsg> DeletedUrlconsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "url-delete");

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
        factory.setConsumerFactory(DeletedUrlconsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, CntUrlKafkaMsg> CntUrlconsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "url-cnt");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(CntUrlKafkaMsg.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CntUrlKafkaMsg> kafkaCntUrlListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CntUrlKafkaMsg> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(CntUrlconsumerFactory());
        return factory;
    }

}
