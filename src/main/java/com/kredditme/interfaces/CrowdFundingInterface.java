/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.interfaces;

import com.kredditme.Models.CrowdFunding;
import com.kredditme.Models.CrowdFundingCreation;
import com.kredditme.Models.Response;

/**
 *
 * @author USER
 */
public interface CrowdFundingInterface {
    
    Response create(CrowdFundingCreation crowdFunding, String token);
    
    CrowdFunding getCrowdFundingByPaymentLink(String paymentLink, String token);
}
