package com.example.ordermanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class OrderDto {
    private UUID id;
    private UUID customerId;
    private String product;
    private Integer quantity;
    private Double price;
    private String status;
}
