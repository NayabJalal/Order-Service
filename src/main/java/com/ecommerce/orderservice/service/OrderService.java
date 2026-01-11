package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService{

    public final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        return null;
    }
}
