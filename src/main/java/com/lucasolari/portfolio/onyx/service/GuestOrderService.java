package com.lucasolari.portfolio.onyx.service;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.domain.persistence.Guest;
import com.lucasolari.portfolio.onyx.domain.persistence.GuestOrder;
import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import com.lucasolari.portfolio.onyx.repository.GuestOrderRepository;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class GuestOrderService {

    private ProductService productService;
    private GuestService guestService;
    private GuestOrderRepository guestOrderRepository;

    @Autowired
    public GuestOrderService(ProductService productService, GuestService guestService, GuestOrderRepository guestOrderRepository) {
        this.productService = productService;
        this.guestService = guestService;
        this.guestOrderRepository = guestOrderRepository;
    }

    @Transactional(rollbackFor = { Exception.class, Error.class })
    public void createPendingGuestOrder(Cart cart,String stripeCheckoutSessionId){

        Product product = productService.findProductById(cart.getProduct().getId());
        GuestOrder guestOrder = new GuestOrder(stripeCheckoutSessionId,
                                               null,
                                               "PENDING",
                                               null,
                                               null,
                                               product,
                                               cart.getQty(),
                                               cart.getTotalAmount());
        guestOrderRepository.save(guestOrder);
    }

    public void confirmPendingGuestOrder(GuestOrder guestOrder,Guest guest,String stripePaymentIntent){

        guestOrder.setStatus("CONFIRMED");
        guestOrder.setGuest(guest);
        guestOrder.setStripePaymentIntent(stripePaymentIntent);
        guestOrder.setOrderTimestamp(Instant.now().getEpochSecond());
    }

    @Transactional(rollbackFor = { Exception.class, Error.class })
    public void fulfillOrder(Session stripeCheckoutSession){

        if(!(stripeCheckoutSession.getPaymentStatus().equals("unpaid"))){

            GuestOrder guestOrder = guestOrderRepository.findGuestOrderByStripeCheckoutSessionId(stripeCheckoutSession.getId());
            if(!(guestOrder.getStatus().equals("CONFIRMED"))){

                Guest extractedGuest = guestService.extractGuestFromStripeCheckoutSession(stripeCheckoutSession);
                Guest savedGuest = guestService.saveGuest(extractedGuest);
                confirmPendingGuestOrder(guestOrder,savedGuest, stripeCheckoutSession.getPaymentIntent());
            }
        }
    }
}
