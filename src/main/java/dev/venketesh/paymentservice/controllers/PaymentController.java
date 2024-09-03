package dev.venketesh.paymentservice.controllers;


import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.venketesh.paymentservice.dtos.InitiatePaymentRequestDto;
import dev.venketesh.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws StripeException, RazorpayException {
        return paymentService.initiatePayment(requestDto.getOrderId(), requestDto.getAmount());
    }
}
