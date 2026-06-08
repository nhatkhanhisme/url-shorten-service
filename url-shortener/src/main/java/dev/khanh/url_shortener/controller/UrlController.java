package dev.khanh.url_shortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.khanh.url_shortener.dto.CreateUrlRequest;
import dev.khanh.url_shortener.dto.UpdateUrlRequest;
import dev.khanh.url_shortener.dto.UrlResponse;
import dev.khanh.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shorten")
@RequiredArgsConstructor
public class UrlController {
  
  private final UrlService urlService;

  @PostMapping
  public ResponseEntity<UrlResponse> create(@RequestBody @Valid CreateUrlRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(urlService.create(request));
  }

  @GetMapping("/{shortCode}")
  public ResponseEntity<UrlResponse> get(@PathVariable String shortCode) {
    return ResponseEntity.ok(urlService.getByShortCode(shortCode));
  }
  
  @PutMapping("/{shortCode}")
  public ResponseEntity<UrlResponse> update(@PathVariable String shortCode, @RequestBody @Valid UpdateUrlRequest request) {
    return ResponseEntity.ok(urlService.update(shortCode, request));
  }


  @DeleteMapping("/{shortCode}")
  public ResponseEntity<Void> delete(@PathVariable String shortCode) {
    urlService.delete(shortCode);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{shortCode}/stats")
  public ResponseEntity<UrlResponse> getStats(@PathVariable String shortCode) {
    return ResponseEntity.ok(urlService.getStats(shortCode));

}
}