package com.elearning.billingservice.controller;

import com.elearning.billingservice.model.Payment;
import com.elearning.billingservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @GetMapping("/user/{userId}")
    public List<Payment> getUserPayments(@PathVariable Long userId) {
        return paymentService.getUserPayments(userId);
    }
}
