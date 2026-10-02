package com.furnora.furnora_backend.service;

import com.furnora.furnora_backend.entity.*;
import com.furnora.furnora_backend.repository.CartItemRepository;
import com.furnora.furnora_backend.repository.OrderRepository;
import com.furnora.furnora_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order checkout(User user) {
        Cart cart = cartService.getCartByUser(user);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot checkout");
        }

        // Total amount kadh
        double totalAmount = 0;
        for (CartItem item : cart.getItems()) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        // Navin Order banav
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus(Order.Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>());

        // Pratyek cart item cha OrderItem banav
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice()); // sadhyacha price save kar

            order.getItems().add(orderItem);

            // Stock kami kar
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }

        Order savedOrder = orderRepository.save(order);

        // Cart rikama kar
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();

        return savedOrder;
    }

    public List<Order> getOrderHistory(User user) {
        return orderRepository.findByUserId(user.getId());
    }

    public Order getOrderById(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("This order does not belong to you");
        }

        return order;
    }

    public Order updateStatus(Long orderId, Order.Status newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}