package com.ecommerce.orderservice.dto;


import lombok.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

//When req will actually come to us, what should be present in the request
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    //Now we do not have JWT it,  so we are hard Coding the userID
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    @NotEmpty(message = "Items cannot be empty")
    private List<OrderItemRequestDTO> items;
}
