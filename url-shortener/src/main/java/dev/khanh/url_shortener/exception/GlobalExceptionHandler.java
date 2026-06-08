package dev.khanh.url_shortener.exception;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<Map<String, String>> handleUrlNotFoundException(UrlNotFoundException ex) {
   return ResponseEntity.status(HttpStatus.NOT_FOUND)
   .body(Map.of("error", ex.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
      .map(e -> e.getField() + ": " + e.getDefaultMessage())
      .toList();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(Map.of("error", errors));
  }
}
