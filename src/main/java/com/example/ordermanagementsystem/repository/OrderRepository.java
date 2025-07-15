package com.example.ordermanagementsystem.repository;

import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.model.Customer;
import com.example.ordermanagementsystem.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCustomer(Customer customer);
    Page<Order> findByCustomer(Customer customer, Pageable pageable);
    List<Order> findByStatus(OrderStatus status);
}
