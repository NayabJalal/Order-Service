package com.ecommerce.orderservice.dto;

import lombok.*;

//api response from product table will come as a productDTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private Long categoryId;
    private String category;
    private String image;
}
