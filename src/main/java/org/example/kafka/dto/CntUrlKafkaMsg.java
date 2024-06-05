package org.example.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CntUrlKafkaMsg(
        @JsonProperty
        String shortUrl
) {
}
