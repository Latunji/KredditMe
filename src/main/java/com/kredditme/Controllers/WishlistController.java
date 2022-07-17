/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Controllers;

import com.kredditme.Models.Item;
import com.kredditme.Models.Response;
import com.kredditme.Models.Wishlist;
import com.kredditme.Models.WishlistResponse;
import com.kredditme.Services.WishlistService;
import com.kredditme.pojo.WishlistPojo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */

@RestController
public class WishlistController {
    
    @Autowired
    WishlistService apiService;

    @CrossOrigin
    @RequestMapping(value = "/item/create", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> createItem(@RequestHeader("Authorization") String token, @RequestParam("name") String name, @RequestParam("icon") String icon,
            @RequestParam("amount") Double amount) {
        return new ResponseEntity<>(apiService.createItem(name, icon, amount, token), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/item/get", method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<>(apiService.getItems(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/wishlist/create/{userId}", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WishlistResponse> createWishlist(@RequestHeader("Authorization") String token, @RequestBody WishlistPojo wishList) {
        return new ResponseEntity<>(apiService.createWishlist(wishList, token), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/wishlist/getByPaymentLink", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Wishlist>> getWishlistByPaymentLink(@RequestHeader("Authorization") String token, @RequestParam("paymentLink") String link) {
        return new ResponseEntity<>(apiService.getWishlistByPaymentLink(link, token), HttpStatus.OK);
    }

    
}
