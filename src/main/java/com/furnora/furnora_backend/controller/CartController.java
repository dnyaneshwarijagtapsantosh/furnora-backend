package com.furnora.furnora_backend.controller;

import com.furnora.furnora_backend.entity.Cart;
import com.furnora.furnora_backend.entity.User;
import com.furnora.furnora_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping
    public ResponseEntity<Cart> getMyCart() {
        return ResponseEntity.ok(cartService.getCartByUser(getCurrentUser()));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Map<String, Object> request) {
        Long productId = Long.valueOf(request.get("productId").toString());
        Integer quantity = Integer.valueOf(request.get("quantity").toString());
        return ResponseEntity.ok(cartService.addToCart(getCurrentUser(), productId, quantity));
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<Cart> updateQuantity(@PathVariable Long cartItemId, @RequestBody Map<String, Object> request) {
        Integer quantity = Integer.valueOf(request.get("quantity").toString());
        return ResponseEntity.ok(cartService.updateItemQuantity(getCurrentUser(), cartItemId, quantity));
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<Cart> removeItem(@PathVariable Long cartItemId) {
        return ResponseEntity.ok(cartService.removeItem(getCurrentUser(), cartItemId));
    }
}