package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dto.CustomerDto;
import com.example.ordermanagementsystem.model.Customer;

import java.util.UUID;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto dto);
    CustomerDto getCustomerById(UUID id);
    Customer getCustomerEntityById(UUID id);
}
