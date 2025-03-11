package com.lucasolari.portfolio.onyx.service;

import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import com.lucasolari.portfolio.onyx.exceptions.product.ProductNotExistentException;
import com.lucasolari.portfolio.onyx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Product findProductById(Long id)throws ProductNotExistentException{

        Optional<Product> productOptional =  productRepository.findById(id);
        if(productOptional.isEmpty()) throw new ProductNotExistentException();
        return productOptional.get();
    }
}
