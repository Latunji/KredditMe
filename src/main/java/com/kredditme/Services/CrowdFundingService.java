/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Services;

import com.kredditme.Models.CrowdFunding;
import com.kredditme.Models.CrowdFundingCreation;
import com.kredditme.Models.Response;
import com.kredditme.abstractentities.CustomPredicate;
import com.kredditme.interfaces.CrowdFundingInterface;
import com.kredditme.pojo.CrowdFundingResponsePojo;
import com.kredditme.utilities.IppmsUtils;
import com.kredditme.utilities.RestCall;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */
@Service
public class CrowdFundingService implements CrowdFundingInterface {
    
    @Autowired
    GenericService genericService;
    
    @Autowired
    RestCall restCall;
    
//    private static String CROWDFUNDING_PAYMENT_LINK = "https://kreddit.me/pay/crowdfunding/";

    @Override
    public CrowdFundingResponsePojo create(CrowdFundingCreation crowdFunding, String token) {
        Logger.getLogger(CrowdFundingService.class.getName()).log(Level.INFO, "CrowdFunding Request: {0}", crowdFunding);
        Logger.getLogger(CrowdFundingService.class.getName()).log(Level.INFO, "Token: {0}", token);
        CrowdFundingResponsePojo resp = new CrowdFundingResponsePojo();
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
        
        String link = IppmsUtils.generateUniquePayRef();
        
        CrowdFunding cFunding = new CrowdFunding();
        cFunding.setAmount(crowdFunding.getAmount());
        cFunding.setDescription(crowdFunding.getDescription());
        cFunding.setHeadline(crowdFunding.getHeadline());
        cFunding.setUserId(crowdFunding.getUserId());
        cFunding.setLinkRef(link);
        cFunding.setSelf(crowdFunding.isSelf());
        cFunding.setAccountNumber(crowdFunding.getAccountNumber());
        cFunding.setBankAccountName(crowdFunding.getBankAccountName());
        cFunding.setBankName(crowdFunding.getBankName());
        
        
        if(IppmsUtils.isNotNull(crowdFunding.getImage())){
            cFunding.setImage(Base64.getDecoder().decode(crowdFunding.getImage()));
        }
        this.genericService.saveObject(cFunding);
        
        resp.setResponseCode("00");
        resp.setResponseMessage("Created Successfully!");
        resp.setLinkRef(link);
        
        return resp;
    }

    @Override
    public CrowdFunding getCrowdFundingByPaymentLink(String linkRef, String token) {
        String fullLink = linkRef;
        Response resp = new Response();
        CrowdFunding cF = new CrowdFunding();
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
            return cF;
        }
        
        try {
            cF = this.genericService.loadObjectWithSingleCondition(CrowdFunding.class, new CustomPredicate("linkRef",
                    fullLink));
        } catch (IllegalAccessException | InstantiationException ex) {
            
            Logger.getLogger(CrowdFundingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cF;
    }
    
}
