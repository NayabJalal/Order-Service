package com.ecommerce.orderservice.clients;


import com.ecommerce.orderservice.dto.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    public ProductServiceClient(RestTemplate restTemplate){
        this.restTemplate =restTemplate;
    }
    public ProductDTO getProductById(Long productId){
        //RestTemplate restTemplate= restTemplateBuilder.build();
        //Not the best way > Service Discovery > netflix eureka - no hardCoding required
        String url = "http://ECOMMERCESPRING:3000/api/products/" + productId;
        ResponseEntity<ProductDTO> response =restTemplate.getForEntity(url,ProductDTO.class);
        return response.getBody();
    }
}
