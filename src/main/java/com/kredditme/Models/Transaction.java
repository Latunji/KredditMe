/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbl_transaction")
@SequenceGenerator(name = "transactionSeq", sequenceName = "tbl_transaction_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable {
    
    @Id
    @GeneratedValue(generator = "transactionSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;
    
    @Column(name = "amount", nullable = false)
    private String amount;
    
    @Column(name = "reference", nullable = false)
    private String reference;
    
    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;
    
    @Column(name = "channel")
    private String channel;
    
    
}
