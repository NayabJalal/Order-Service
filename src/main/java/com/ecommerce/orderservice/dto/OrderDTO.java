package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.enums.OrderStatus;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private OrderStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<OrderItemDTO> items;
}
