/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Models;

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
public class WishlistResponse {
    private String responseCode;
    private String responseMessage;
    private String paymentLink;
}
