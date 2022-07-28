/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Services;

import com.kredditme.Models.CrowdFunding;
import com.kredditme.Models.Item;
import com.kredditme.Models.Response;
import com.kredditme.Models.Transaction;
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
import com.kredditme.pojo.PaymentDto;
import com.kredditme.pojo.PaymentResponseDto;
import com.kredditme.pojo.VerifyPaymentDto;
import com.kredditme.pojo.WishlistVerifyPaymentDto;
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

//    private static String WISHLIST_PAYMENT_LINK = "https://kreddit.me/pay/wishlist/";

    @Override
    public Response createItem(String name, String icon, String token) {
        Item item = new Item();
        Response resp = new Response();
        
        JSONObject js = new JSONObject();
        JSONObject body = new JSONObject();
        try {
            body.put("token", token);
            js = new JSONObject(restCall.executeRequest(body));
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
        String link = IppmsUtils.generateUniquePayRef();
        WishlistResponse resp = new WishlistResponse();
        
        JSONObject js = new JSONObject();
        JSONObject body = new JSONObject();
        try {
            body.put("token", token);
            js = new JSONObject(restCall.executeRequest(body));
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
                wish.setLinkRef(link);
                wish.setSelf(wishlist.isSelf());
                wish.setAccountNumber(wishlist.getAccountNumber());
                wish.setBankAccountName(wishlist.getBankAccountName());
                wish.setBankName(wishlist.getBankName());
                this.genericService.saveObject(wish);

                resp.setResponseCode("00");
                resp.setResponseMessage("Wishlist Created Successfully");
                resp.setLinkRef(link);
                
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
        JSONObject body = new JSONObject();
        try {
            body.put("token", token);
            js = new JSONObject(restCall.executeRequest(body));
        } catch (JSONException | IOException ex) {
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(IppmsUtils.isNull(js)){
            return wish;
        }
        
        wish = this.genericService.loadAllObjectsWithSingleCondition(Wishlist.class, 
                new CustomPredicate("linkRef", link), "name");
        
        return wish;
    }
    
    @Override
    public PaymentResponseDto initializePayment(PaymentDto payment) {
        
        PaymentResponseDto paymentResponse = new PaymentResponseDto();
        String js = null;
        JSONObject response = new JSONObject();
        try {
            js = restCall.executeRequest(payment.getEmail(), payment.getAmount());
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.INFO, "verify payment output...{0}", js);
        } catch (JSONException | IOException ex) {
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
        }
                if(js != null){
                    response = new JSONObject(js);
                    paymentResponse.setAuthUrl(response.getJSONObject("data").get("authorization_url").toString());
                    paymentResponse.setAccessCode(response.getJSONObject("data").get("access_code").toString());
                    paymentResponse.setReference(response.getJSONObject("data").get("reference").toString());
                    paymentResponse.setResponseCode("00");
                    paymentResponse.setResponseDesc("Succcessful");
                }
                else{
                    paymentResponse.setResponseCode("96");
                    paymentResponse.setResponseDesc("Error Occured");
                }
                
                return paymentResponse;
    }
    
    @Override
    public Response verifyPayment(WishlistVerifyPaymentDto payment) {
        
        Response resp = new Response();
        
        String js = null;
        JSONObject response = new JSONObject();
        Transaction transaction = new Transaction();
        Wishlist cF = new Wishlist();
        String amount;
        try {
            js = restCall.executeVerifyPayment(payment.getReference());
            Logger.getLogger(WishlistService.class.getName()).log(Level.INFO, "verify payment output...{0}", js);
        } catch (JSONException | IOException ex) {
            Logger.getLogger(WishlistService.class.getName()).log(Level.SEVERE, null, ex);
        }
                if(js != null){
                    response = new JSONObject(js);
                    amount = response.getJSONObject("data").get("amount").toString();
                    transaction.setAmount(amount);
                    transaction.setTransactionDate(response.getJSONObject("data").get("transaction_date").toString());
                    transaction.setReference(response.getJSONObject("data").get("reference").toString());
                    transaction.setChannel(response.getJSONObject("data").get("channel").toString());
                    transaction.setUserId(payment.getLinkRef());
                    
                    this.genericService.saveObject(transaction);
                    
            try {
                cF = this.genericService.loadObjectWithSingleCondition(Wishlist.class,
                        new CustomPredicate("linkRef", payment.getLinkRef()));
            } catch (IllegalAccessException | InstantiationException ex) {
                Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(IppmsUtils.isNotNull(cF)){
                cF.setAmountPaid(cF.getAmountPaid()+ Double.valueOf(amount));
                this.genericService.saveOrUpdate(cF);
            }
                    
                    resp.setResponseCode("00");
                    resp.setResponseMessage("Succcessful");
                }
                else{
                    resp.setResponseCode("96");
                    resp.setResponseMessage("Error Occurred");
                }
                return resp;
    }
}

