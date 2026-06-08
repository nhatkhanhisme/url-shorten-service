package dev.khanh.url_shortener.dto;

import java.time.Instant;

import dev.khanh.url_shortener.entity.UrlEntity;

public record UrlResponse(
  String id,
  String url, 
  String shortCode,
  Instant createdAt,
  Instant updatedAt,
  Long accessCount
) {
  public static UrlResponse from(UrlEntity entity) {
    return new UrlResponse(
      entity.getUrlId().toString(),
      entity.getUrl(),
      entity.getShortCode(),
      entity.getCreatedAt(),
      entity.getUpdatedAt(),
      entity.getAccessCount()
    );
  }
}
