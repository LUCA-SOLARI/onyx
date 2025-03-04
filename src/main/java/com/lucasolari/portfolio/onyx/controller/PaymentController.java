package com.lucasolari.portfolio.onyx.controller;

import com.google.gson.JsonSyntaxException;
import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.service.CartService;
import com.lucasolari.portfolio.onyx.service.GuestOrderService;
import com.lucasolari.portfolio.onyx.service.PaymentService;
import com.lucasolari.portfolio.onyx.service.SessionService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;
    private GuestOrderService guestOrderService;
    private SessionService sessionService;
    private CartService cartService;

    @Autowired
    public PaymentController(PaymentService paymentService, GuestOrderService guestOrderService, SessionService sessionService, CartService cartService) {
        this.paymentService = paymentService;
        this.guestOrderService = guestOrderService;
        this.sessionService = sessionService;
        this.cartService = cartService;
    }

    @GetMapping("/success")
    public String success(HttpServletRequest request,
                          @RequestParam(value="session_id",required=true) String sessionId,
                          Model model){

        //Invalidate session
        HttpSession session = request.getSession(false);
        if(sessionService.sessionExists(session)){
            sessionService.invalidateSession(session);
        };

        try{
            //If Stripe API doesn't find the checkout session with the
            //specified id is gonna throw a StripeException
            Session stripeCheckoutSession = paymentService.retrieveStripeCheckoutSession(sessionId);
            guestOrderService.fulfillOrder(stripeCheckoutSession);
            model.addAttribute("order_identifier",stripeCheckoutSession.getPaymentIntent());
            return "payment/success";
        }catch(StripeException stripeException){
            return "server-error";
        }
    }

    @GetMapping("/cancel")
    public String error(){
        return "payment/cancel";
    }

    @PostMapping("/create-stripe-checkout-session")
    public String createStripeCheckoutSession(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(!(sessionService.sessionExists(session))) return "redirect:/";
        Cart cart = cartService.retrieveCartFromSession(session);

        try{
            return "redirect:" + paymentService.createStripeCheckoutSession(cart);
        }catch(StripeException stripeException){
            System.out.println(stripeException.getStripeError().getMessage());
            return "server-error";
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(HttpServletRequest request){

        String STRIPE_WEBHOOK_SECRET = "whsec_X3b7jiGxENQ8WorviBCIy2Jd1KdOFZYd";
        String payload = null;
        String sigHeader = request.getHeader("Stripe-Signature");
        Event event = null;

        try {
            // Read the payload from the request body
            payload = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            // Verify the webhook signature to ensure the event is from Stripe
            event = Webhook.constructEvent(payload, sigHeader, STRIPE_WEBHOOK_SECRET);
        } catch (JsonSyntaxException e) {
            // Invalid payload (malformed JSON)
            return ResponseEntity.status(400).body("Invalid payload");
        } catch (SignatureVerificationException e) {
            // Invalid signature
            return ResponseEntity.status(400).body("Invalid signature");
        }catch( Exception exception ){
            return ResponseEntity.status(400).body("Error");
        }

        if ("checkout.session.completed".equals(event.getType())) {

            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;

            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                return ResponseEntity.status(400).body("Error");
            }
            Session stripeCheckoutSession = (Session)stripeObject;
            guestOrderService.fulfillOrder(stripeCheckoutSession);
        }
        return ResponseEntity.status(200).body("");
    }
}
