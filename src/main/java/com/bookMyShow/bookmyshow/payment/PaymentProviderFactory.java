package com.bookMyShow.bookmyshow.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
public class PaymentProviderFactory {

    private final Map<PaymentProvider, PaymentService> paymentProviderMap;

    @Autowired
    public PaymentProviderFactory(List<PaymentService> paymentProviderList) {
        paymentProviderMap = paymentProviderList.stream().collect(toMap(PaymentService::getPaymentProvider, Function.identity()));
    }


    public PaymentService getPaymentProcessor(PaymentProvider paymentProvider) {
        return paymentProviderMap.get(paymentProvider);
    }
}
