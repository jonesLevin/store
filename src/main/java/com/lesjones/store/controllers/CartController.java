package com.lesjones.store.controllers;

import com.lesjones.store.dtos.AddItemToCartRequest;
import com.lesjones.store.dtos.CartDto;
import com.lesjones.store.dtos.CartItemDto;
import com.lesjones.store.dtos.UpdateCartItemRequest;
import com.lesjones.store.exceptions.CartNotFoundException;
import com.lesjones.store.exceptions.ProductNotFoundException;
import com.lesjones.store.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @Valid @RequestBody AddItemToCartRequest request,
            UriComponentsBuilder uriBuilder) {

        var cartItemDto = cartService.addToCart(cartId, request.getProductId());

        var uri = uriBuilder.path("/cart/{id}").buildAndExpand(cartId).toUri();

        return ResponseEntity.created(uri).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable UUID cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping
    public List<CartDto> getAllCarts() {
        return cartService.getCarts();
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        return cartService.updateItem(cartId, productId, request.getQuantity());
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(@PathVariable UUID cartId, @PathVariable Long productId) {
        cartService.removeItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Cart not found.")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", "Product not found in the cart.")
        );
    }
}
