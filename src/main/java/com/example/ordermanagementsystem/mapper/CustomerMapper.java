package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dto.CustomerDto;
import com.example.ordermanagementsystem.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}

