package com.ecommerce.orderservice.dto;


import lombok.*;

import java.util.List;

//When req will actually come to us, what should be present in the request
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    //Now we do not have JWT it,  so we are hard Coding the userID
    private Long userID;
    private List<OrderItemRequestDTO> items;
}
