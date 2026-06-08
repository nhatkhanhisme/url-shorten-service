package dev.khanh.url_shortener.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class ShortCodeGenerator {
  private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int CODE_LENGTH = 6;
  private final SecureRandom random = new SecureRandom();

  public String generate() {
    StringBuilder sb = new StringBuilder(CODE_LENGTH);
    for (int i = 0; i < CODE_LENGTH; i++) {
      sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
    }
    return sb.toString();
  }
}
