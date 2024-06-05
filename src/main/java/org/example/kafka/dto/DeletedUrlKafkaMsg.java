package org.example.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeletedUrlKafkaMsg(
        @JsonProperty
        String shortUrl
) {
}
