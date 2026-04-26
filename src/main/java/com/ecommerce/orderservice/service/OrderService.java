package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.clients.ProductServiceClient;
import com.ecommerce.orderservice.dto.CreateOrderResponseDTO;
import com.ecommerce.orderservice.dto.OrderDTO;
import com.ecommerce.orderservice.dto.OrderItemRequestDTO;
import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.dto.ProductDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.enums.OrderStatus;
import com.ecommerce.orderservice.exceptions.OrderNotFoundException;
import com.ecommerce.orderservice.exceptions.ProductNotFoundException;
import com.ecommerce.orderservice.mapper.OrderItemMapper;
import com.ecommerce.orderservice.mapper.OrderMapper;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Order order = OrderMapper.toEntity(request); //orderEntity
        List<OrderItem> items = new ArrayList<>(); //List of all the items with was sent

        for (OrderItemRequestDTO itemDTO : request.getItems()){ //iterate all the items one by one
            //fetch the product details for every item, first we have to expose the product client;
            try {
                ProductDTO product = productClient.getProductById(itemDTO.getProductId());
                if (product == null) {
                    throw new ProductNotFoundException(itemDTO.getProductId());
                }
                //Calculation here --
                double pricePerUnit = product.getPrice();
                double totalPrice = pricePerUnit * itemDTO.getQuantity();

                //OrderItemDTO
                OrderItem item = OrderItemMapper.orderItemRequestDTOtoOrderItemEntity(
                        itemDTO,
                        order,
                        pricePerUnit,
                        totalPrice
                );
                items.add(item);
            } catch (Exception e) {
                throw new ProductNotFoundException("Product with id " + itemDTO.getProductId() + " not found or service unavailable");
            }
        }

        order.setItems(items);
        Order createdOrder = orderRepository.save(order);
        return OrderMapper.toCreateOrderResponseDTO(createdOrder);
        //Entry Got added for both order and order_Items, The entry automatically got cascaded,got created,got passed on for orderItems as well
        //Even though I haven't called OrderItem Repo,neither created it - it is because of the CASCADING Effect;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return OrderMapper.toOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
