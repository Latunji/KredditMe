/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "tbl_wishlist")
@SequenceGenerator(name = "wishlistSeq", sequenceName = "tbl_wishlist_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Wishlist implements Serializable{
    
    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "wishlistSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "wishlist_id", nullable = false, unique = true)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    
    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @Column(name = "link_ref", nullable = false)
    private String linkRef;
    
    @Column(name = "paid")
    private boolean paid;
    
    @Column(name = "amount_paid", columnDefinition = "numeric(15,2) default '0.00'")
    private double amountPaid;
    
    @Column(name = "self")
    private boolean self;
    
    @Column(name = "account_number")
    private String accountNumber;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "bank_account_name")
    private String bankAccountName;
    
}
