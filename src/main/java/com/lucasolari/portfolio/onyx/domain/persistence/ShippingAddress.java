package com.lucasolari.portfolio.onyx.domain.persistence;

import jakarta.persistence.Embeddable;

@Embeddable
public class ShippingAddress {

    //Two-letter country code
    private String country;
    private String state;
    private String city;
    private String postalCode;
    //Address line 1 (e.g., street, PO Box, or company name
    private String line1;
    //Address line 2 (e.g., apartment, suite, unit, or building
    private String line2;

    public ShippingAddress() {
    }

    public ShippingAddress(String country, String state, String city, String postalCode, String line1, String line2) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
        this.line1 = line1;
        this.line2 = line2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }
}
