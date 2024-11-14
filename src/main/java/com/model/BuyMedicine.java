package com.model;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="buymedicine")
public class BuyMedicine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
	
//	@ManyToOne
//	@JoinColumn(name = "medicineid", referencedColumnName = "medicineid", insertable = false, updatable = false)
//	private Medicine medicine;
//	
    @Column(name="medicineid")
    private Integer medicineid;
    
    @Column(name="quantity")
    private Integer quantity;
    
    @Column(name="price")
    private Integer price;
    
    @Column(name = "makedate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate  makedate;
    
    @Column(name = "expairdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate  expairdate;
    
    @Column(name="entryby", length = 255)
    private String entryby;
    
    @Column(name = "entrydate", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate entrydate;
    
   
	public BuyMedicine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BuyMedicine(Long id, Integer medicineid, Integer quantity,Integer price, LocalDate makedate, LocalDate expairdate, String entryby,
			LocalDate entrydate) {
		super();
		this.id = id;
		this.medicineid = medicineid;
		this.quantity = quantity;
		this.price =price;
		this.makedate = makedate;
		this.expairdate = expairdate;
		this.entryby = entryby;
		this.entrydate = entrydate;
	}
	
	
	
    @PrePersist
    protected void onCreate() {
    	entrydate = LocalDate.now();
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
	
	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}



	public LocalDate getMakedate() {
		return makedate;
	}


	public void setMakedate(LocalDate makedate) {
		this.makedate = makedate;
	}


	public LocalDate getExpairdate() {
		return expairdate;
	}


	public void setExpairdate(LocalDate expairdate) {
		this.expairdate = expairdate;
	}


	public String getEntryby() {
		return entryby;
	}


	public void setEntryby(String entryby) {
		this.entryby = entryby;
	}


	public LocalDate getEntrydate() {
		return entrydate;
	}


	public void setEntrydate(LocalDate entrydate) {
		this.entrydate = entrydate;
	}
    
	
 

}



