package com.lucasolari.portfolio.onyx.domain;

import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import java.math.BigDecimal;

public class Cart {

    private Product product;
    private int qty;

    public Cart(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getTotalAmount(){

            BigDecimal productPrice = product.getPrice();
            BigDecimal total = new BigDecimal("0.00");
            for(int counter = 0;counter<qty;counter++){
                   total = total.add(productPrice);
            }
            return total;
        }
    }

