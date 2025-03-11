package com.lucasolari.portfolio.onyx.domain.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Access(AccessType.FIELD)
public class Product{

    @Id
    private Long id;
    private String name;
    private String description;
    private String pictureUrl;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String description, String pictureUrl, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}