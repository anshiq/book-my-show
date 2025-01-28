package com.bookMyShow.bookmyshow.payment;


public interface PaymentService {
    void processPayment(int amount);

    PaymentProvider getPaymentProvider();
}



