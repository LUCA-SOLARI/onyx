package com.lucasolari.portfolio.onyx.controller;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.exceptions.product.ProductNotExistentException;
import com.lucasolari.portfolio.onyx.service.CartService;
import com.lucasolari.portfolio.onyx.service.ProductService;
import com.lucasolari.portfolio.onyx.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

        HttpSession session = request.getSession(false);
        if(sessionService.sessionExists(session)){
           Cart cart = cartService.retrieveCartFromSession(session);
           cartService.addToCart(cart);
           return "redirect:/";
        }else{
            try{
                cartService.initializeCartSession(productId,sessionService.createSession(request));
                return "redirect:/";
            }catch(ProductNotExistentException productNotExistentException){
                System.out.println("ProductNotExistentException");
                return "server-error";
            }catch(DataAccessException dataAccessException){
                System.out.println("DataAccessException");
                System.out.println(dataAccessException.getMessage());
                return "server-error";
            }catch(Exception exception){
                System.out.println("Exception !!!");
                System.out.println(exception.getMessage());
                return "server-error";
            }
        }
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
            Cart cart = cartService.retrieveCartFromSession(session);
            cartService.updateCart(cart,action);
            return "redirect:/cart";
        }
    }
}
