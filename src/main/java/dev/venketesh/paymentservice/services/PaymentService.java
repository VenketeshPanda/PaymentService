package dev.venketesh.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.venketesh.paymentservice.paymentgateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentGateway paymentGateway;

    PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway=paymentGateway;
    }

    public String initiatePayment(Long orderId,Long amount) throws StripeException, RazorpayException {
        return paymentGateway.generatePaymentLink(orderId,amount);
    }
}
