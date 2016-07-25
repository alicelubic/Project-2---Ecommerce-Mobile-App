package com.example.owlslubic.project2;

import java.util.ArrayList;

/**
 * Created by owlslubic on 7/22/16.
 */
public class ShoppingCartSingleton {
    private static ShoppingCartSingleton shoppingCart = null;
    private static ArrayList<Product> shoppingCartContents;

    //singleton keeps a list of my Product objects that have been added to the cart
    public static ShoppingCartSingleton getInstance(){
        if(shoppingCart==null){
            shoppingCart = new ShoppingCartSingleton();
        }
        return shoppingCart;
    }

    public void addItem(Product product){
        shoppingCartContents.add(product);

        //if item already exists, increment quantity
    }
    public void removeItem(Product product){

        //if item quantity > 1, i--, if quantity == 1, remove product from shoppingCartContents
    }




}
