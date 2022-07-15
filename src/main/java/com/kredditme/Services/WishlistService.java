/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Services;

import com.kredditme.Models.Item;
import com.kredditme.Models.Response;
import com.kredditme.Models.Wishlist;
import com.kredditme.Models.WishlistResponse;
import com.kredditme.abstractentities.CustomPredicate;
import com.kredditme.pojo.WishlistPojo;
import com.kredditme.utilities.IConstants;
import com.kredditme.utilities.IppmsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.kredditme.interfaces.WishlistInterface;
import com.kredditme.utilities.RestCall;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
@Service
public class WishlistService implements WishlistInterface {

    @Autowired
    GenericService genericService;
    
    @Autowired
    RestCall restCall;

    private static String WISHLIST_PAYMENT_LINK = "https://kreddit.me/pay/wishlist/";

    @Override
    public Response createItem(String name, String icon, Double amount, String token) {
        Item item = new Item();
        Response resp = new Response();
        
        JSONObject js = new JSONObject();
        try {
            js = new JSONObject(restCall.authenticate(token));
        } catch (JSONException | IOException ex) {
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(IppmsUtils.isNull(js)){
            resp.setResponseCode("01");
            resp.setResponseMessage("Authentication Failed");
            return resp;
        }
        
        try {
            item = this.genericService.loadObjectWithSingleCondition(Item.class, new CustomPredicate("name", name));
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(WishlistService.class.getName()).log(Level.SEVERE, null, ex);
            resp.setResponseCode("01");
            resp.setResponseMessage("Item already exist");
            return resp;
        }
        if (IppmsUtils.isNotNullOrEmpty(item.getName())) {
            item.setAmount(amount);
            item.setIcon(icon);
            item.setName(name);

            this.genericService.saveObject(item);
            resp.setResponseCode("00");
            resp.setResponseMessage("Item Saved Successfully");
        } else {
            resp.setResponseCode("01");
            resp.setResponseMessage("Item already exist");
            return resp;
        }

        return resp;
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try {
            items = this.genericService.loadAllObjectsWithoutRestrictions(Item.class, "name");
        } catch (Exception ex) {
            Logger.getLogger(WishlistService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    @Override
    public WishlistResponse createWishlist(WishlistPojo wishlist, String token) {
        String link = WISHLIST_PAYMENT_LINK + IppmsUtils.generateUniquePayRef();
        WishlistResponse resp = new WishlistResponse();
        
        JSONObject js = new JSONObject();
        try {
            js = new JSONObject(restCall.authenticate(token));
        } catch (JSONException | IOException ex) {
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(IppmsUtils.isNull(js)){
            resp.setResponseCode("01");
            resp.setResponseMessage("Authentication Failed");
            return resp;
        }
        try {
            for (int i = 0; i < wishlist.getItemId().size(); i++) {
                Long w;
                Wishlist wish = new Wishlist();
                w = (Long) wishlist.getItemId().get(i);
                wish.setItem(this.genericService.loadObjectById(Item.class, w));
                wish.setUserId(wishlist.getUserId());
                wish.setPaymentLink(link);
                wish.setSelf(wishlist.isSelf());
                this.genericService.saveObject(wish);

                resp.setResponseCode("00");
                resp.setResponseMessage("Wishlist Created Successfully");
                resp.setPaymentLink(link);
                
            }
        } catch (DataAccessException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(WishlistService.class.getName()).log(Level.SEVERE, null, ex);
            resp.setResponseCode("01");
            resp.setResponseMessage("Wishlist not created");
        }
        return resp;
    }

    @Override
    public List<Wishlist> getWishlistByPaymentLink(String link, String token) {
        List<Wishlist> wish = new ArrayList<>();
        
        JSONObject js = new JSONObject();
        try {
            js = new JSONObject(restCall.authenticate(token));
        } catch (JSONException | IOException ex) {
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(IppmsUtils.isNull(js)){
            return wish;
        }
        
        wish = this.genericService.loadAllObjectsWithSingleCondition(Wishlist.class, 
                new CustomPredicate("paymentLink", link), "name");
        
        return wish;
    }
}

