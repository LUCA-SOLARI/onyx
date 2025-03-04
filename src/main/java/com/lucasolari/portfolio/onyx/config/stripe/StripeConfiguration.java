package com.lucasolari.portfolio.onyx.config.stripe;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {

    public StripeConfiguration(@Value("${stripe.secretApiKey}") String stripeSecretApiKey){
        Stripe.apiKey=stripeSecretApiKey;
    }
}
