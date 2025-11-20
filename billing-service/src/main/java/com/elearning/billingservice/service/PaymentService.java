package com.elearning.billingservice.service;

import com.elearning.billingservice.model.Payment;
import com.elearning.billingservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        // Mock Stripe Integration
        // In reality, we would call Stripe API here
        payment.setStatus("SUCCESS");
        payment.setTransactionDate(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public List<Payment> getUserPayments(Long userId) {
        return paymentRepository.findByUserId(userId);
    }
}
