/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.Models;

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
@Table(name = "tbl_item_selected")
@SequenceGenerator(name = "itemSelectedSeq", sequenceName = "tbl_item_selected_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class ItemSelected {
    
    private static final long serialVersionUID = 1840603817779318751L;
    
    @Id
    @GeneratedValue(generator = "itemSelectedSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "item_selected_id", nullable = false, unique = true)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    
    @Column(name = "amount",  columnDefinition = "numeric(15,2) default '0.00'")
    private Double amount;
    
    @Column(name = "userId")
    private String userId;
    
}
