package com.example.ordermanagementsystem.service.impl;

import com.example.ordermanagementsystem.dto.OrderDto;
import com.example.ordermanagementsystem.exception.NotFoundException;
import com.example.ordermanagementsystem.mapper.OrderMapper;
import com.example.ordermanagementsystem.model.*;
import com.example.ordermanagementsystem.repository.OrderRepository;
import com.example.ordermanagementsystem.service.CustomerService;
import com.example.ordermanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto dto) {
        Order order = orderMapper.toEntity(dto);
        Customer customer = customerService.getCustomerEntityById(dto.getCustomerId());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
         return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(UUID id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(OrderStatus.valueOf(status));
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByStatus(String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        return orderRepository.findByStatus(orderStatus)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getOrdersByCustomerId(UUID customerId, Pageable pageable) {
        Customer customer = customerService.getCustomerEntityById(customerId);
        return orderRepository.findByCustomer(customer, pageable)
                .map(orderMapper::toDto);
    }
}

