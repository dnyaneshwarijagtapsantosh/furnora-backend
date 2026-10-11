package com.furnora.furnora_backend.service;

import com.furnora.furnora_backend.entity.Cart;
import com.furnora.furnora_backend.entity.CartItem;
import com.furnora.furnora_backend.entity.Product;
import com.furnora.furnora_backend.entity.User;
import com.furnora.furnora_backend.repository.CartItemRepository;
import com.furnora.furnora_backend.repository.CartRepository;
import com.furnora.furnora_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartByUser(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));
    }

    public Cart addToCart(User user, Long productId, Integer quantity) {
        Cart cart = getCartByUser(user);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        LocalDate today = LocalDate.now();

        // Fakt AAJ add kelela toch product asel tar quantity vadhav
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .filter(item -> item.getAddedAt() != null && item.getAddedAt().toLocalDate().equals(today))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setAddedAt(LocalDateTime.now());
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        return getCartByUser(user);
    }

    public Cart updateItemQuantity(User user, Long cartItemId, Integer quantity) {
        Cart cart = getCartByUser(user);

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("This item does not belong to your cart");
        }

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return getCartByUser(user);
    }

    public Cart removeItem(User user, Long cartItemId) {
        Cart cart = getCartByUser(user);

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("This item does not belong to your cart");
        }

        cart.getItems().remove(item);
        cartItemRepository.delete(item);

        return getCartByUser(user);
    }
}