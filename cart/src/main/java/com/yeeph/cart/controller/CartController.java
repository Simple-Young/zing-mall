package com.yeeph.cart.controller;

import com.yeeph.cart.service.CartService;
import com.yeeph.cart.vo.CartItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/getCheckedItems")
    public List<CartItemVo> getCheckedItems() {
        return cartService.getCheckedItems();
    }
}
