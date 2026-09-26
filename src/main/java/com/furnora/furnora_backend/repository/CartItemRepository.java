package com.furnora.furnora_backend.repository;

import com.furnora.furnora_backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}