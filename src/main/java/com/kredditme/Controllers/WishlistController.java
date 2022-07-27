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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    
    @Autowired
    WishlistService apiService;

    @PostMapping(value = "/item/create", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createItem(@RequestHeader("Authorization") String token, @RequestParam("name") String name, @RequestParam("icon") String icon,
            @RequestParam("amount") Double amount) {
        return new ResponseEntity<>(apiService.createItem(name, icon, token), HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/wishlist/submitItem", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> submitItem(@RequestHeader("Authorization") String token, @RequestParam("name") String name, @RequestParam("icon") String icon,
            @RequestParam("amount") Double amount) {
        return new ResponseEntity<>(apiService.createItem(name, icon, token), HttpStatus.OK);
    }
    
    @GetMapping(value = "/item/get", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<>(apiService.getItems(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/create/{userId}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WishlistResponse> createWishlist(@RequestHeader("Authorization") String token, @RequestBody WishlistPojo wishList) {
        return new ResponseEntity<>(apiService.createWishlist(wishList, token), HttpStatus.OK);
    }
    
    @PostMapping(value = "/getByLinkRef", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wishlist>> getWishlistByPaymentLink(@RequestHeader("Authorization") String token, @RequestParam("paymentLink") String link) {
        return new ResponseEntity<>(apiService.getWishlistByPaymentLink(link, token), HttpStatus.OK);
    }

    
}
