package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medicine")
public class Medicine {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name="medicineid")
    private Integer medicineid;
    
    @Column(name="medicinename", length = 255)
    private String medicinename;
    
    @Column(name="medicinedesc", length = 255)
    private String medicinedesc;
    
    @Column(name="chategoryid", length = 255)
    private String chategoryid;
    
    @Column(name="supplierid", length = 255)
    private String supplierid;

	public Medicine() {
	}

	public Medicine(Long id, Integer medicineid, String medicinename, String medicinedesc, String chategoryid,
			String supplierid) {
		super();
		this.id = id;
		this.medicineid = medicineid;
		this.medicinename = medicinename;
		this.medicinedesc = medicinedesc;
		this.chategoryid = chategoryid;
		this.supplierid = supplierid;
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

	public String getMedicinename() {
		return medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	public String getMedicinedesc() {
		return medicinedesc;
	}

	public void setMedicinedesc(String medicinedesc) {
		this.medicinedesc = medicinedesc;
	}

	public String getChategoryid() {
		return chategoryid;
	}

	public void setChategoryid(String chategoryid) {
		this.chategoryid = chategoryid;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}	    
	
	
	
	
	
	

}
