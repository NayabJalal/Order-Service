package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.OrderItemDTO;
import com.ecommerce.orderservice.dto.OrderItemRequestDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;

import java.math.BigDecimal;

public class OrderItemMapper {
    public static OrderItem orderItemRequestDTOtoOrderItemEntity(OrderItemRequestDTO itemDTO, Order order , double pricePerUnit, double totalPrice){
        return OrderItem.builder()
                .productId(itemDTO.getProductId())
                .quantity(itemDTO.getQuantity())
                .totalPrice(totalPrice)
                .pricePerUnit(pricePerUnit)
                .order(order)
                .build();
    }

    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .pricePerUnit(BigDecimal.valueOf(orderItem.getPricePerUnit()))
                .totalPrice(BigDecimal.valueOf(orderItem.getTotalPrice()))
                .build();
    }
}
