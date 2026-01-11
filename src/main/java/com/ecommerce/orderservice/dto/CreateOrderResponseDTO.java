package com.ecommerce.orderservice.dto;

import com.ecommerce.orderservice.enums.OrderStatus;
import lombok.*;

//for response to send to the user;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponseDTO {
    private Long orderId;
    private OrderStatus status;
}
