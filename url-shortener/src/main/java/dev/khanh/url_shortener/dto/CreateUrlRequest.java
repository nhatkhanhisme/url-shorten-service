package dev.khanh.url_shortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record CreateUrlRequest(@NotBlank @URL String url) {
}
