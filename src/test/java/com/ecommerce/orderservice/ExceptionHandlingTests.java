package com.ecommerce.orderservice;

import com.ecommerce.orderservice.dto.OrderRequestDTO;
import com.ecommerce.orderservice.dto.OrderItemRequestDTO;
import com.ecommerce.orderservice.exceptions.OrderNotFoundException;
import com.ecommerce.orderservice.exceptions.ProductNotFoundException;
import com.ecommerce.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionHandlingTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    /**
     * Test validation failure for null userId
     */
    @Test
    public void testCreateOrderWithNullUserId() throws Exception {
        String requestBody = """
                {
                    "userId": null,
                    "items": [
                        {
                            "productId": 1,
                            "quantity": 2
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.userId").exists());
    }

    /**
     * Test validation failure for empty items list
     */
    @Test
    public void testCreateOrderWithEmptyItems() throws Exception {
        String requestBody = """
                {
                    "userId": 1,
                    "items": []
                }
                """;

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.items").exists());
    }

    /**
     * Test validation failure for quantity less than 1
     */
    @Test
    public void testCreateOrderWithInvalidQuantity() throws Exception {
        String requestBody = """
                {
                    "userId": 1,
                    "items": [
                        {
                            "productId": 1,
                            "quantity": 0
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400));
    }

    /**
     * Test order not found exception
     */
    @Test
    public void testGetNonExistentOrder() throws Exception {
        mockMvc.perform(get("/api/orders/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.errorType").value("ORDER_NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Order not found")));
    }

    /**
     * Test custom exception throwing in service
     */
    @Test
    public void testOrderNotFoundExceptionHandling() {
        try {
            orderService.getOrderById(999L);
        } catch (OrderNotFoundException e) {
            assert e.getMessage().contains("Order not found");
        }
    }
}

