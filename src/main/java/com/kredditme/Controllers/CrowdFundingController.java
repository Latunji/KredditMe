/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Controllers;

import com.kredditme.Models.CrowdFunding;
import com.kredditme.Models.CrowdFundingCreation;
import com.kredditme.Models.Response;
import com.kredditme.Models.Wishlist;
import com.kredditme.Services.CrowdFundingService;
import com.kredditme.pojo.CrowdFundingResponsePojo;
import com.kredditme.pojo.PaymentLinkDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/crowdFunding")
public class CrowdFundingController {
    
    @Autowired
    CrowdFundingService crowdFundingService;
   
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrowdFundingResponsePojo> createCrowdFunding(@RequestHeader("Authorization") String token, @RequestBody CrowdFundingCreation crowdfunding) {
        return new ResponseEntity<>(crowdFundingService.create(crowdfunding, token), HttpStatus.OK);
    }
    
   
    @PostMapping(value = "/getByPaymentLink", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrowdFunding> getCrowdFundingByPaymentLink(@RequestHeader("Authorization") String token, @RequestBody PaymentLinkDto link) {
        return new ResponseEntity<>(crowdFundingService.getCrowdFundingByPaymentLink(link.getPaymentLink(), token), HttpStatus.OK);
    }
    
}
