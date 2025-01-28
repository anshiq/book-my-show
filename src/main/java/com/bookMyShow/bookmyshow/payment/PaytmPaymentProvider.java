package com.bookMyShow.bookmyshow.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaytmPaymentProvider implements PaymentService {
    @Override
    public void processPayment(int amount) {
        log.info("Processing payment of Rs. {} through Paytm", amount);
    }

    @Override
    public PaymentProvider getPaymentProvider() {
        return PaymentProvider.PAYTM;
    }
}
