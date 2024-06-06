package org.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlResponseDto(@JsonProperty String url) {
}
