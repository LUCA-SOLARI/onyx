package com.lucasolari.portfolio.onyx.controller;

import com.lucasolari.portfolio.onyx.domain.Cart;
import com.lucasolari.portfolio.onyx.domain.persistence.Product;
import com.lucasolari.portfolio.onyx.exceptions.product.ProductNotExistentException;
import com.lucasolari.portfolio.onyx.service.CartService;
import com.lucasolari.portfolio.onyx.service.ProductService;
import com.lucasolari.portfolio.onyx.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

        Product product = null;

        try{
             product = productService.findProductById(1L);
             System.out.println("Am I printed ?");
             model.addAttribute("product",product);
             return "main";
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