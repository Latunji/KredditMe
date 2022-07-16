/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.pojo;

import java.util.List;
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
public class WishlistPojo {
    private List itemId;
    private Long userId;
    private boolean self;
    private String accountNumber;
    private String bankName;
    private String bankAccountName;
}
