package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;

public interface IOrderService {
    CreateOrderResponseDTO createOrder (OrderRequestDTO requestDTO);
}
