package com.lucasolari.portfolio.onyx.service;

import com.lucasolari.portfolio.onyx.domain.persistence.Guest;
import com.lucasolari.portfolio.onyx.domain.persistence.ShippingAddress;
import com.lucasolari.portfolio.onyx.repository.GuestRepository;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest extractGuestFromStripeCheckoutSession(Session stripeCheckoutSession){

        String fullName = stripeCheckoutSession.getShippingDetails().getName();
        String email = stripeCheckoutSession.getCustomerDetails().getEmail();
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCountry(stripeCheckoutSession.getShippingDetails().getAddress().getCountry());
        shippingAddress.setState(stripeCheckoutSession.getShippingDetails().getAddress().getState());
        shippingAddress.setCity(stripeCheckoutSession.getShippingDetails().getAddress().getCity());
        shippingAddress.setPostalCode(stripeCheckoutSession.getShippingDetails().getAddress().getPostalCode());
        shippingAddress.setLine1(stripeCheckoutSession.getShippingDetails().getAddress().getLine1());
        shippingAddress.setLine2(stripeCheckoutSession.getShippingDetails().getAddress().getLine2());
        return new Guest(fullName,email,shippingAddress);
    }

    @Transactional(rollbackFor = { Exception.class, Error.class })
    public Guest saveGuest(Guest guest){
        return guestRepository.save(guest);
    }

}
