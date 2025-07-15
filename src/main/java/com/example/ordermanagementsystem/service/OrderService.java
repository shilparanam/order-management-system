package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDto createOrder(OrderDto dto);
    OrderDto getOrderById(UUID id);
    OrderDto updateOrderStatus(UUID id, String status);
    List<OrderDto> getOrdersByStatus(String status);
    Page<OrderDto> getOrdersByCustomerId(UUID customerId, Pageable pageable);
}
