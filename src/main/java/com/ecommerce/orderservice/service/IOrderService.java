package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.enums.OrderStatus;

import java.util.List;

public interface IOrderService {
    CreateOrderResponseDTO createOrder (OrderRequestDTO requestDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersByUserId(Long userId);
    void updateOrderStatus(Long id, OrderStatus status);
}
