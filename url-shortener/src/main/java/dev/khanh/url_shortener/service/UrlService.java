package dev.khanh.url_shortener.service;

import org.springframework.stereotype.Service;

import dev.khanh.url_shortener.dto.CreateUrlRequest;
import dev.khanh.url_shortener.dto.UpdateUrlRequest;
import dev.khanh.url_shortener.dto.UrlResponse;
import dev.khanh.url_shortener.entity.UrlEntity;
import dev.khanh.url_shortener.exception.UrlNotFoundException;
import dev.khanh.url_shortener.repository.UrlRepository;
import dev.khanh.url_shortener.utils.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {

  private final UrlRepository urlRepository;
  private final ShortCodeGenerator shortCodeGenerator;

  public UrlResponse create(CreateUrlRequest request) {
    String shortCode = generateUniqueShortCode();

    UrlEntity urlEntity = new UrlEntity();
    urlEntity.setUrl(request.url());
    urlEntity.setShortCode(shortCode);
    UrlEntity savedEntity = urlRepository.save(urlEntity);

    log.info("Created short URL: {} -> {}", shortCode, request.url());

    return UrlResponse.from(savedEntity);
  }

  public UrlResponse getByShortCode(String shortCode) {
    UrlEntity entity = findOrThrow(shortCode);
    entity.setAccessCount(entity.getAccessCount() + 1);
    return UrlResponse.from(urlRepository.save(entity));
  }

  public UrlResponse update(String shortCode, UpdateUrlRequest request) {
    UrlEntity urlEntity = findOrThrow(shortCode);
    urlEntity.setUrl(request.url());
    UrlEntity updatedEntity = urlRepository.save(urlEntity);

    log.info("Updated short URL: {} -> {}", shortCode, request.url());

    return UrlResponse.from(updatedEntity);
  }

  public void delete(String shortCode) {
    UrlEntity urlEntity = findOrThrow(shortCode);
    urlRepository.delete(urlEntity);

    log.info("Deleted short URL: {}", shortCode);
  }

  public UrlResponse getStats(String code) {
    return UrlResponse.from(findOrThrow(code));
  }
  // --- private helper method ---

  private UrlEntity findOrThrow(String code) {
    return urlRepository.findByShortCode(code)
        .orElseThrow(() -> new UrlNotFoundException(code));
  }

  private String generateUniqueShortCode() {
    String code;
    int attempts = 0;
    do {
      code = shortCodeGenerator.generate();
      attempts++;
      if (attempts > 10) {
        throw new IllegalStateException("Failed to generate unique short code after 10 attempts");
    }
    } while (urlRepository.existsByShortCode(code));
    return code;
  }
}
