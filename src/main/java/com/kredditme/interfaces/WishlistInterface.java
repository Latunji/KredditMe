/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.interfaces;

import com.kredditme.Models.Item;
import com.kredditme.Models.Response;
import com.kredditme.Models.Wishlist;
import com.kredditme.Models.WishlistResponse;
import com.kredditme.pojo.WishlistPojo;
import java.util.List;

/**
 *
 * @author USER
 */
public interface WishlistInterface {
    
    Response createItem(String name, String icon, Double amount);
    
    List<Item> getItems();
    
    WishlistResponse createWishlist(WishlistPojo wishlist);
    
    List<Wishlist> getWishlistByPaymentLink(String link);
    
}
