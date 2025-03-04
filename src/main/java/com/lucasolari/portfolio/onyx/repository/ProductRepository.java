package com.lucasolari.portfolio.onyx.repository;

import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p")
    Product retrieveProduct();
}

//This method might throw these exceptions :

//1)Caused by: org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned
//The method only works if a single product is present
//If there are no products, Product is null