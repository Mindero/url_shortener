package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlRequestDto(@JsonProperty String url, @JsonProperty Boolean onlyOnce) {
}
