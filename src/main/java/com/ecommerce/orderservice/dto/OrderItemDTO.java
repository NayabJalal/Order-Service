package com.ecommerce.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    private Long productId;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
}
