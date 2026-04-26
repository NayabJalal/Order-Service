package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderDTO;
import com.ecommerce.orderservice.dto.OrderItemDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.enums.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toEntity(OrderRequestDTO dto){
        return Order.builder()
                .userId(dto.getUserId())
                .status(OrderStatus.PENDING)
                .build();
    }
    public static CreateOrderResponseDTO toCreateOrderResponseDTO(Order order){
        return CreateOrderResponseDTO.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .build();
    }

    public static OrderDTO toOrderDTO(Order order) {
        List<OrderItemDTO> items = order.getItems().stream()
                .map(OrderItemMapper::toOrderItemDTO)
                .collect(Collectors.toList());
        return OrderDTO.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .items(items)
                .build();
    }
}
