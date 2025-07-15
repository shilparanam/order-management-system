package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dto.OrderDto;
import com.example.ordermanagementsystem.model.Order;
import com.example.ordermanagementsystem.model.OrderStatus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-14T23:48:37-0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setProduct( order.getProduct() );
        orderDto.setQuantity( order.getQuantity() );
        orderDto.setPrice( order.getPrice() );
        if ( order.getStatus() != null ) {
            orderDto.setStatus( order.getStatus().name() );
        }

        return orderDto;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDto.getId() );
        order.product( orderDto.getProduct() );
        order.quantity( orderDto.getQuantity() );
        order.price( orderDto.getPrice() );
        if ( orderDto.getStatus() != null ) {
            order.status( Enum.valueOf( OrderStatus.class, orderDto.getStatus() ) );
        }

        return order.build();
    }
}
