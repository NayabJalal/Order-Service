package com.ecommerce.orderservice.exceptions;

import com.ecommerce.orderservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage()));
        
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Validation failed");
        response.put("timestamp", Instant.now());
        response.put("path", request.getDescription(false).replace("uri=", ""));
        response.put("errors", fieldErrors);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .errorType("ORDER_NOT_FOUND")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .errorType("PRODUCT_NOT_FOUND")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOrderRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidOrderRequestException(InvalidOrderRequestException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timestamp(Instant.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .errorType("INVALID_ORDER_REQUEST")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred: " + ex.getMessage())
                .timestamp(Instant.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .errorType("INTERNAL_SERVER_ERROR")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An error occurred: " + ex.getMessage())
                .timestamp(Instant.now())
                .path(request.getDescription(false).replace("uri=", ""))
                .errorType("GENERAL_ERROR")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

