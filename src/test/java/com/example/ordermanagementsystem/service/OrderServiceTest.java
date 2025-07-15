package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dto.OrderDto;
import com.example.ordermanagementsystem.mapper.CustomerMapper;
import com.example.ordermanagementsystem.mapper.OrderMapper;
import com.example.ordermanagementsystem.model.Customer;
import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.model.OrderStatus;
import com.example.ordermanagementsystem.repository.OrderRepository;
import com.example.ordermanagementsystem.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindOrderById_ReturnsOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.valueOf("NEW"));
        OrderDto orderDTDto = new OrderDto();
        orderDTDto.setId(orderId);
        orderDTDto.setStatus("NEW");
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toDto(order)).thenReturn(orderDTDto);

        // Act
        OrderDto result = orderService.getOrderById(orderId);

        // Assert
        assertNotNull(result);
        assertEquals("NEW", result.getStatus());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testFindOrderById_ReturnsNullWhenNotFound() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        assertThrows(com.example.ordermanagementsystem.exception.NotFoundException.class, () -> {
            orderService.getOrderById(orderId);
        });

        // Assert
       verify(orderRepository, times(1)).findById(orderId);
    }



}
