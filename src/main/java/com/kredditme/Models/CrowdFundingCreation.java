/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
public class CrowdFundingCreation {
    
    private String userId;
    
    private String headline;
    
    private String description;
    
    private String image;
    
    private double amount;
    
    private boolean self;
    
    private String accountNumber;
    
    private String bankName;
    
    private String bankAccountName;
    
}
