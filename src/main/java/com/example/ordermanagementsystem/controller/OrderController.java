package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dto.OrderDto;
import com.example.ordermanagementsystem.dto.UpdateOrderStatusDto;
import com.example.ordermanagementsystem.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name="Order", description = "Order management operations")
public class OrderController {
    private final OrderService orderService;

     @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Place a new order")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {

        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "View order details by ID")
    public ResponseEntity<OrderDto> getOrder(@PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update order status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrderStatusDto status) {
        if (status.getStatus() == null || status.getStatus().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status.getStatus()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Search orders by status")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(String status) {
        if (status != null) {
            return ResponseEntity.ok(orderService.getOrdersByStatus(status));
        }
        return ResponseEntity.badRequest().build();
    }
}
