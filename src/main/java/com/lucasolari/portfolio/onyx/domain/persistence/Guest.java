package com.lucasolari.portfolio.onyx.domain.persistence;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class Guest{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    @Embedded
    private ShippingAddress shippingAddress;

    public Guest() {
    }

    public Guest(String fullName, String email, ShippingAddress shippingAddress) {
        this.fullName = fullName;
        this.email = email;
        this.shippingAddress = shippingAddress;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}



