/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "tbl_crowd_funding")
@SequenceGenerator(name = "crowdFundingSeq", sequenceName = "tbl_crowd_funding_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class CrowdFunding implements Serializable {
    
    @Id
    @GeneratedValue(generator = "crowdFundingSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "crowd_funding_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @Column(name = "headline", nullable = false)
    private String headline;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "link_ref", nullable = false)
    private String linkRef;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob @Column(name = "image")
    protected byte[] image;
    
    @Column(name = "amount", columnDefinition = "numeric(15,2) default '0.00'")
    private double amount;
    
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
