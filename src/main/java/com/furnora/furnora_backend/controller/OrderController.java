package com.furnora.furnora_backend.controller;

import com.furnora.furnora_backend.entity.Order;
import com.furnora.furnora_backend.entity.User;
import com.furnora.furnora_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout() {
        return ResponseEntity.ok(orderService.checkout(getCurrentUser()));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders() {
        return ResponseEntity.ok(orderService.getOrderHistory(getCurrentUser()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId, getCurrentUser()));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long orderId, @RequestBody Map<String, String> request) {
        Order.Status status = Order.Status.valueOf(request.get("status"));
        return ResponseEntity.ok(orderService.updateStatus(orderId, status));
    }
}