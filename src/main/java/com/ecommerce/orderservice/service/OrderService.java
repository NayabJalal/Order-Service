package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.clients.ProductServiceClient;
import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderItemRequestDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.dto.ProductDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.mapper.OrderItemMapper;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    public final OrderRepository orderRepository;
    public final ProductServiceClient productClient;

    public OrderService(OrderRepository orderRepository, ProductServiceClient productClient){
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }
    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = OrderMapper.toEntity(request);
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequestDTO itemDTO : request.getItems()){
            //fetch the product details for every item, first we have to expose the product client;
            ProductDTO product = productClient.getProductById(itemDTO.getProductID());
            double pricePerUnit = product.getPrice();
            double totalPrice = pricePerUnit * itemDTO.getQuantity();

            OrderItem item = OrderItemMapper.orderItemRequestDTOtoOrderItemEntity(
                    itemDTO,
                    order,
                    pricePerUnit,
                    totalPrice
            );
            items.add(item);
        }

        order.setItems(items);
        Order createdOrder = orderRepository.save(order);
        return OrderMapper.toCreateOrderResponseDTO(createdOrder);
    }
}
