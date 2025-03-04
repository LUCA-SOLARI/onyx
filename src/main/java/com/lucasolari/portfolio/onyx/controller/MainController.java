package com.lucasolari.portfolio.onyx.controller;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import com.lucasolari.portfolio.onyx.service.CartService;
import com.lucasolari.portfolio.onyx.service.ProductService;
import com.lucasolari.portfolio.onyx.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private ProductService productService;
    private SessionService sessionService;
    private CartService cartService;

    @Autowired
    public MainController(ProductService productService, SessionService sessionService, CartService cartService) {
        this.productService = productService;
        this.sessionService = sessionService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String main(Model model,
                       HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(sessionService.sessionExists(session)){
           Cart cart = cartService.retrieveCartFromSession(session);
           if(cart.getQty()>0) model.addAttribute("cart_qty",cart.getQty());
        }
        Product product = productService.findProductById(1L);
        model.addAttribute("product",product);
        return "main";
    }

}