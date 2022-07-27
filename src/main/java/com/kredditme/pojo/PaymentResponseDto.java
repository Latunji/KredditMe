/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Data
@Getter
@Setter
public class PaymentResponseDto {
    
    private String responseCode;
    private String responseDesc;
    private String authUrl;
    private String accessCode;
    private String reference;
    
    
}
