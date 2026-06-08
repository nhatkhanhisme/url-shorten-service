package dev.khanh.url_shortener.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "urls")
@Getter
@Setter
public class UrlEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer urlId;

  @Column(nullable = false)
  private String url;

  @Column(name = "short_code", unique = true, nullable = false, length = 10)
  private String shortCode;

  @Column(name = "access_count", nullable = false)
  private Long accessCount=0L;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

}
