package com.model;

import java.time.LocalDate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="buymedicine")
public class BuyMedicine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
	
    @Column(name="medicineid")
    private Integer medicineid;
    
    @Column(name="quantity")
    private Integer quantity;
    
    @Column(name = "makedate")  
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date makedate;
    
    @Column(name = "expairdate")  
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date expairdate;
    
    @Column(name="entryby", length = 255)
    private String entryby;
    
    @Column(name = "entrydate", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate entryDate;
    
   
	public BuyMedicine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BuyMedicine(Long id, Integer medicineid, Integer quantity, Date makedate, Date expairdate, String entryby,
			LocalDate entryDate) {
		super();
		this.id = id;
		this.medicineid = medicineid;
		this.quantity = quantity;
		this.makedate = makedate;
		this.expairdate = expairdate;
		this.entryby = entryby;
		this.entryDate = entryDate;
	}
    @PrePersist
    protected void onCreate() {
    	entryDate = LocalDate.now();
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getMedicineid() {
		return medicineid;
	}


	public void setMedicineid(Integer medicineid) {
		this.medicineid = medicineid;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Date getMakedate() {
		return makedate;
	}


	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}


	public Date getExpairdate() {
		return expairdate;
	}


	public void setExpairdate(Date expairdate) {
		this.expairdate = expairdate;
	}


	public String getEntryby() {
		return entryby;
	}


	public void setEntryby(String entryby) {
		this.entryby = entryby;
	}


	public LocalDate getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
    
	
 

}



