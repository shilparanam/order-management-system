package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dto.CustomerDto;
import com.example.ordermanagementsystem.mapper.CustomerMapper;
import com.example.ordermanagementsystem.model.Customer;
import com.example.ordermanagementsystem.repository.CustomerRepository;
import com.example.ordermanagementsystem.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;;

    @Mock
    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCustomerById_ReturnsCustomer() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setName("John Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        // Act
        CustomerDto result = customerService.getCustomerById(customerId);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerMapper, times(1)).toDto(customer);
    }

    @Test
    void testFindCustomerById_ReturnsNullWhenNotFound() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

       // Assert
        // Act
        assertThrows(com.example.ordermanagementsystem.exception.NotFoundException.class, () -> {
            customerService.getCustomerById(customerId);
        });
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerMapper, never()).toDto(any());

    }

    @Test
    void testSaveCustomer_Success() {
        // Arrange
        CustomerDto customer = new CustomerDto();
        customer.setName("Jane Doe");
        customer.setEmail("hjane@gmail.com");
        Customer customerEntity = new Customer();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        when(customerMapper.toEntity(customer)).thenReturn(customerEntity);
        when(customerRepository.save(any(Customer.class))).thenReturn(customerEntity);
        when(customerMapper.toDto(customerEntity)).thenReturn(customer);


        // Act
        CustomerDto savedCustomer = customerService.createCustomer(customer);

        // Assert
        assertNotNull(savedCustomer);
        assertEquals("Jane Doe", savedCustomer.getName());

    }


}
