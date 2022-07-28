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
public class PaymentDto {
    
    private String email;
    private String amount;
    
}
