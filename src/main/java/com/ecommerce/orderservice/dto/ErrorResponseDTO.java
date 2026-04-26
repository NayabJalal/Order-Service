package com.ecommerce.orderservice.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
    private int statusCode;
    private String message;
    private Instant timestamp;
    private String path;
    private String errorType;
}

