package com.jakie.book.test;

import com.jakie.book.pojo.Cart;
import com.jakie.book.pojo.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addItem() {
        Cart cart = new Cart();
        CartItem java = new CartItem(1, "JAVA", 10, new BigDecimal(100), new BigDecimal(1000));
        CartItem java1 = new CartItem(1, "JAVA", 10, new BigDecimal(100), new BigDecimal(1000));
        CartItem c = new CartItem(2, "C", 1, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(java);
        cart.addItem(java1);
        cart.addItem(c);
        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        Cart cart = new Cart();
        CartItem java = new CartItem(1, "JAVA", 10, new BigDecimal(100), new BigDecimal(1000));
        CartItem c = new CartItem(2, "C", 1, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(java);
        cart.addItem(c);

        System.out.println(cart);

        cart.deleteItem(2);

        System.out.println(cart);

    }

    @Test
    void clear() {
        Cart cart = new Cart();
        CartItem java = new CartItem(1, "JAVA", 10, new BigDecimal(100), new BigDecimal(1000));
        CartItem c = new CartItem(2, "C", 1, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(java);
        cart.addItem(c);

        System.out.println(cart);

        cart.clear();

        System.out.println(cart);

    }

    @Test
    void updateCount() {
        Cart cart = new Cart();
        CartItem java = new CartItem(1, "JAVA", 10, new BigDecimal(100), new BigDecimal(1000));
        CartItem c = new CartItem(2, "C", 1, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(java);
        cart.addItem(c);

        System.out.println(cart);

        cart.updateCount(2,10);

        System.out.println(cart);
    }
}