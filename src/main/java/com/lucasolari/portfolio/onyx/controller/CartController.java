package com.lucasolari.portfolio.onyx.controller;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.exceptions.cart.CartBadRequestException;
import com.lucasolari.portfolio.onyx.service.CartService;
import com.lucasolari.portfolio.onyx.service.ProductService;
import com.lucasolari.portfolio.onyx.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private SessionService sessionService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, SessionService sessionService) {
        this.cartService = cartService;
        this.productService = productService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getCart(Model model,
                          HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(sessionService.sessionExists(session)){
            Cart cart = cartService.retrieveCartFromSession(session);
            if(cart.getQty()==0) return "empty-cart";
            model.addAttribute("cart",cart);
            return "cart";
        }else{
            return "empty-cart";
        }
    }

    @PostMapping("/add")
    public String addToCart(HttpServletRequest request,
                            @RequestParam(value="id",required=true) Long productId){

        //Add validation id format here : not null, not empty, only valid numbers
        HttpSession session = request.getSession(false);
        if(sessionService.sessionExists(session)){
           Cart cart = cartService.retrieveCartFromSession(session);
           cartService.addToCart(cart,productId);
        }else{
            cartService.initializeCartSession(productId,sessionService.createSession(request));
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateCart(HttpServletRequest request,
                             @RequestParam(value="action",required=true)
                             @Pattern(regexp = "add|sub")
                             String action){

        HttpSession session = request.getSession(false);
        if(!(sessionService.sessionExists(session))){
            return "redirect:/";
        }else{
            try{
                Cart cart = cartService.retrieveCartFromSession(session);
                cartService.updateCart(cart,action);
            }catch(CartBadRequestException cartBadRequestException){
                //Simply ignore
            }
            return "redirect:/cart";
        }
    }
}
