/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.interfaces;

import com.kredditme.Models.CrowdFunding;
import com.kredditme.Models.CrowdFundingCreation;
import com.kredditme.Models.Response;
import com.kredditme.pojo.CrowdFundingResponsePojo;
import com.kredditme.pojo.PaymentDto;
import com.kredditme.pojo.PaymentResponseDto;
import com.kredditme.pojo.VerifyPaymentDto;

/**
 *
 * @author USER
 */
public interface CrowdFundingInterface {
    
    CrowdFundingResponsePojo create(CrowdFundingCreation crowdFunding, String token);
    
    CrowdFunding getCrowdFundingByPaymentLink(String paymentLink, String token);
    
    PaymentResponseDto initializePayment(PaymentDto payment);
    
    Response verifyPayment(VerifyPaymentDto payment);

}
