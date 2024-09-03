package dev.venketesh.paymentservice.paymentgateway;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class StripePG implements PaymentGateway{

    @Value("${STRIPE_API_KEY}")
    private String stripeSecretKey;

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount) //10.00
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build())
                        .build();

        Price price = Price.create(priceParams);

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId().toString())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                                                        .build())
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .build()
                        ).build();
        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.toString();
    }
}
