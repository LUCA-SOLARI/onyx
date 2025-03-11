package com.lucasolari.portfolio.onyx.repository;

import com.lucasolari.portfolio.onyx.domain.persistence.GuestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface GuestOrderRepository extends JpaRepository<GuestOrder,Long> {

    @Query("SELECT go FROM GuestOrder go WHERE go.stripeCheckoutSessionId = :stripeCheckoutSessionId")
    GuestOrder findGuestOrderByStripeCheckoutSessionId(String stripeCheckoutSessionId);
}
