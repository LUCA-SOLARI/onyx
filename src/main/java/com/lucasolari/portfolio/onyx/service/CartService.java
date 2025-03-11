package com.lucasolari.portfolio.onyx.service;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import com.lucasolari.portfolio.onyx.exceptions.product.ProductNotExistentException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private ProductService productService;

    @Autowired
    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Cart cart){
            if((cart.getQty() + 1) != 11){
                cart.setQty(cart.getQty() + 1);
            }
        }


    public void initializeCartSession(Long productId,HttpSession session)throws ProductNotExistentException {

        Product product = productService.findProductById(productId);
        Cart cart = new Cart(product,1);
        session.setAttribute("cart",cart);
    }

    public void updateCart(Cart cart,String action){

        if(!(cart.getQty()==0)){
            if(action.equals("add") &&
              (!((cart.getQty() + 1) == 11))){
                cart.setQty(cart.getQty() + 1);
            }
            if(action.equals("sub")) cart.setQty(cart.getQty() - 1);
        }
    }

    public Cart retrieveCartFromSession(HttpSession session){

          return (Cart)session.getAttribute("cart");
    }

}
