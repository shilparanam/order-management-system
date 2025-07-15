package com.example.ordermanagementsystem.config;

import com.example.ordermanagementsystem.model.Customer;
import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.model.OrderStatus;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import com.example.ordermanagementsystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
public class TestDataLoader {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            log.info("Loading test data...");

            // Create customers
            Customer customer1 = customerRepository.save(Customer.builder()
                    .name("John Doe")
                    .email("john.doe@example.com")
                    .build());

            Customer customer2 = customerRepository.save(Customer.builder()
                    .name("Jane Smith")
                    .email("jane.smith@example.com")
                    .build());

            // Create orders
            orderRepository.save(Order.builder()
                    .customer(customer1)
                    .product("Laptop")
                    .quantity(1)
                    .price(999.99)
                    .status(OrderStatus.NEW)
                    .build());

            orderRepository.save(Order.builder()
                    .customer(customer1)
                    .product("Phone")
                    .quantity(2)
                    .price(599.99)
                    .status(OrderStatus.PROCESSING)
                    .build());

            orderRepository.save(Order.builder()
                    .customer(customer2)
                    .product("Tablet")
                    .quantity(1)
                    .price(349.99)
                    .status(OrderStatus.COMPLETED)
                    .build());

            log.info("Test data loaded successfully!");
        };
    }
}