package com.lucasolari.portfolio.onyx.service;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import com.lucasolari.portfolio.onyx.exceptions.cart.CartBadRequestException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private ProductService productService;

    @Autowired
    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Cart cart,Long id){

            cart.setQty(cart.getQty() + 1);
        }


    public void initializeCartSession(Long productId,HttpSession session){

        Product product = productService.findProductById(productId);
        Cart cart = new Cart(product,1);
        session.setAttribute("cart",cart);
    }

    public void updateCart(Cart cart,String action)throws CartBadRequestException{

            if(cart.getQty()==0)throw new CartBadRequestException();
            if(action.equals("add")) cart.setQty(cart.getQty() + 1);
            if(action.equals("sub")) cart.setQty(cart.getQty() - 1);
    }

    public Cart retrieveCartFromSession(HttpSession session){

          return (Cart)session.getAttribute("cart");
    }

}
