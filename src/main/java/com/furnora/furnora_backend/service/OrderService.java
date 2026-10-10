package com.furnora.furnora_backend.service;

import com.furnora.furnora_backend.dto.CheckoutRequest;
import com.furnora.furnora_backend.entity.*;
import com.furnora.furnora_backend.repository.CartItemRepository;
import com.furnora.furnora_backend.repository.OrderRepository;
import com.furnora.furnora_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Order checkout(User user, CheckoutRequest request) {
        if (request == null || isBlank(request.getCustomerName()) || isBlank(request.getPhone())
                || isBlank(request.getAddress()) || isBlank(request.getPaymentMethod())) {
            throw new RuntimeException("Name, phone, address and payment method are required");
        }

        String paymentMethod = request.getPaymentMethod().trim().toUpperCase();
        if (!paymentMethod.equals("COD") && !paymentMethod.equals("ONLINE")) {
            throw new RuntimeException("Invalid payment method");
        }

        Cart cart = cartService.getCartByUser(user);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot checkout");
        }

        // Kahich badlanyachya aadhi sagalya items cha stock tapas
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + item.getProduct().getName());
            }
        }

        double totalAmount = 0;
        for (CartItem item : cart.getItems()) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus(Order.Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerName(request.getCustomerName().trim());
        order.setPhone(request.getPhone().trim());
        order.setAddress(request.getAddress().trim());
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(paymentMethod.equals("ONLINE") ? "PAID_DEMO" : "PAY_ON_DELIVERY");
        order.setItems(new ArrayList<>());

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice()); // sadhyacha price save kar
            order.getItems().add(orderItem);

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

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}