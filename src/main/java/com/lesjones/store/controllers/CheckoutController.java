package com.lesjones.store.controllers;

import com.lesjones.store.dtos.CheckoutRequest;
import com.lesjones.store.dtos.CheckoutResponse;
import com.lesjones.store.dtos.ErrorDto;
import com.lesjones.store.exceptions.CartEmptyException;
import com.lesjones.store.exceptions.CartNotFoundException;
import com.lesjones.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }
}
