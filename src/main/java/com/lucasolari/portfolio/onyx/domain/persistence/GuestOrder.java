package com.lucasolari.portfolio.onyx.domain.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Access(AccessType.FIELD)
public class GuestOrder{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stripeCheckoutSessionId;
    private String stripePaymentIntent;
    private String status;
    private Long orderTimestamp;

    @OneToOne
    @JoinColumn(name = "guest_id",unique = true)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int productQty;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalOrder;

    public GuestOrder() {
    }

    public GuestOrder(String stripeCheckoutSessionId, String stripePaymentIntent, String status, Long orderTimestamp, Guest guest, Product product, int productQty, BigDecimal totalOrder) {
        this.stripeCheckoutSessionId = stripeCheckoutSessionId;
        this.stripePaymentIntent = stripePaymentIntent;
        this.status = status;
        this.orderTimestamp = orderTimestamp;
        this.guest = guest;
        this.product = product;
        this.productQty = productQty;
        this.totalOrder = totalOrder;
    }

    public Long getId() {
        return id;
    }

    public String getStripeCheckoutSessionId() {
        return stripeCheckoutSessionId;
    }

    public void setStripeCheckoutSessionId(String stripeCheckoutSessionId) {
        this.stripeCheckoutSessionId = stripeCheckoutSessionId;
    }

    public String getStripePaymentIntent() {
        return stripePaymentIntent;
    }

    public void setStripePaymentIntent(String stripePaymentIntent) {
        this.stripePaymentIntent = stripePaymentIntent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Long orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }
}

