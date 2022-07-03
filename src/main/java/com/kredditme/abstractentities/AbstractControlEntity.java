/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.abstractentities;


import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oamlserver
 */
@SuppressWarnings("serial")
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractControlEntity {
    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "created_by", nullable = false)
//    protected User createdBy;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "last_mod_by", nullable = false)
//    protected User lastModBy;

    @Column(name = "creation_date", nullable = false)
    protected Timestamp creationDate = Timestamp.from(Instant.now());

    @Column(name = "last_mod_ts")
    protected Timestamp lastModTs;
    
    @Transient
    protected String actualUserName;
    
//    public String getActualUserName() {
//		this.actualUserName = this.getCreatedBy().getUsername();
//		return actualUserName;
//	}
    
    
    
}
