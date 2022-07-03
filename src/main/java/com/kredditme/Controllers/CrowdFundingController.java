/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Controllers;

import com.kredditme.Models.CrowdFunding;
import com.kredditme.Models.Response;
import com.kredditme.Models.Wishlist;
import com.kredditme.Services.CrowdFundingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
public class CrowdFundingController {
    
    @Autowired
    CrowdFundingService crowdFundingService;
   
    
    @RequestMapping(value = "/crowdFunding/create", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> createCrowdFunding(@RequestBody CrowdFunding crowdfunding) {
        return new ResponseEntity<>(crowdFundingService.create(crowdfunding), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/crowdFunding/getByPaymentLink", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CrowdFunding> getCrowdFundingByPaymentLink(@RequestParam("paymentLink") String link) {
        return new ResponseEntity<>(crowdFundingService.getCrowdFundingByPaymentLink(link), HttpStatus.OK);
    }
    
}
