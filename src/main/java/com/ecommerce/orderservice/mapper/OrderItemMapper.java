package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.OrderItemRequestDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;

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
}
