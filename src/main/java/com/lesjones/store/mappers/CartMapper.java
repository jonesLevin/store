package com.lesjones.store.mappers;

import com.lesjones.store.dtos.CartDto;
import com.lesjones.store.dtos.CartItemDto;
import com.lesjones.store.entities.Cart;
import com.lesjones.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
