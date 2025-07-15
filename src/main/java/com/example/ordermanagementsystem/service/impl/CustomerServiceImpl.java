package com.example.ordermanagementsystem.service.impl;

import com.example.ordermanagementsystem.dto.CustomerDto;
import com.example.ordermanagementsystem.exception.NotFoundException;
import com.example.ordermanagementsystem.mapper.CustomerMapper;
import com.example.ordermanagementsystem.model.Customer;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import com.example.ordermanagementsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerEntityById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}

