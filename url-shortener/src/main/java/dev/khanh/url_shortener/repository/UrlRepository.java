package dev.khanh.url_shortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.khanh.url_shortener.entity.UrlEntity;

public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {
  Optional<UrlEntity> findByShortCode(String shortCode);

  boolean existsByShortCode(String shortCode);
}
