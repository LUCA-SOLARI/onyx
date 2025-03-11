package com.lucasolari.portfolio.onyx.repository;

import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {}
