package com.ecommerce.orderservice.dto;


import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequestDTO {
    @NotNull(message = "Product ID cannot be null")
    private Long productId;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
