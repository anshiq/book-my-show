package com.bookMyShow.bookmyshow.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhonePePaymentProvider implements PaymentService {
    @Override
    public void processPayment(int amount) {
        log.info("Processing payment of Rs. {} through Phonepe", amount);
    }

    @Override
    public PaymentProvider getPaymentProvider() {
        return PaymentProvider.PHONEPE;
    }
}
