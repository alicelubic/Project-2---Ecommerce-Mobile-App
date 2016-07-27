package com.example.owlslubic.project2;

import java.util.ArrayList;

/**
 * Created by owlslubic on 7/27/16.
 */
public class CartSingleton {
    private static CartSingleton cart = null;
    private static ArrayList<Plant> cartList;

    private CartSingleton() {
        cartList = new ArrayList<>();
    }

    public static CartSingleton getInstance(){
        if(cart == null){
            cart = new CartSingleton();
        }
        return cart;
    }

    public ArrayList<Plant> getCartList(){
        return cartList;
    }
    public void addToCartSingleton(Plant plant){
        cartList.add(plant);
    }

    public void emptyCart(){
        cartList.removeAll(cartList);
    }


    public void deleteFromCartSingleton(Plant plant){
        cartList.remove(plant);
    }
    public int getNumberOfItemsInCart(){
        return cartList.size();
    }
}
