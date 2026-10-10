package com.furnora.furnora_backend.dto;

import lombok.Data;

@Data
public class CheckoutRequest {
    private String customerName;
    private String phone;
    private String address;
    private String paymentMethod; // "COD" kinva "ONLINE"
}