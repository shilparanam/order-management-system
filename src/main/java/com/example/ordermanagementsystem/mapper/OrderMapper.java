package com.example.ordermanagementsystem.mapper;


import com.example.ordermanagementsystem.dto.OrderDto;
import com.example.ordermanagementsystem.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order order);

    @Mapping(target="customer", ignore = true)
    Order toEntity(OrderDto orderDto);

}

