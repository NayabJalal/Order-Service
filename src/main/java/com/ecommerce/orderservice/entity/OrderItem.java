package com.ecommerce.orderservice.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderItem extends BaseEntity{

    private Long productId;

    private int quantity;

    private double pricePerUnit;

    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
