package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.enums.OrderStatus;

public class OrderMapper {
    public static Order toEntity(OrderRequestDTO dto){
        return Order.builder()
                .userId(dto.getUserID())
                .status(OrderStatus.PENDING)
                .build();
    }
    public static CreateOrderResponseDTO toCreateOrderResponseDTO(Order order){
        return CreateOrderResponseDTO.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .build();
    }
}
