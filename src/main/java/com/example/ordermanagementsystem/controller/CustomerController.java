package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dto.CustomerDto;
import com.example.ordermanagementsystem.dto.OrderDto;
import com.example.ordermanagementsystem.service.CustomerService;
import com.example.ordermanagementsystem.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name="Customer", description = "Customer management operations")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new customer")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Get customer details by ID")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/{id}/orders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Operation(summary = "Get all orders for a customer (paginated)")
    public ResponseEntity<Page<OrderDto>> getOrdersByCustomer(
            @PathVariable UUID id,Pageable pageable) {
        // If pageable is not provided, use Pageable.unpaged()
        Pageable effectivePageable = (pageable == null) ? Pageable.unpaged() : pageable;
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id, effectivePageable));
    }
}
