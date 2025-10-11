package com.lesjones.store.mappers;

import com.lesjones.store.dtos.OrderDto;
import com.lesjones.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
