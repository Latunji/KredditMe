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
    
    Response createItem(String name, String icon, String token);
   
    List<Item> getItems();
    
    WishlistResponse createWishlist(WishlistPojo wishlist, String token);
    
    List<Wishlist> getWishlistByPaymentLink(String link, String token);
    
}
