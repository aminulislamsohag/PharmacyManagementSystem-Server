package com.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
    
//    @ManyToOne
//    @JoinColumn(name = "supplierid", referencedColumnName = "supplierid", insertable = false, updatable = false)
//    private Supplier supplier;
    
    
    @Column(name="supplierid", length = 255)
    private Integer supplierid;
    
    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate;


	public Medicine() {
	}


	public Medicine(Long id, Integer medicineid, String medicinename, String medicinedesc, String chategoryid,
			Integer supplierid, LocalDate createdDate) {
		super();
		this.id = id;
		this.medicineid = medicineid;
		this.medicinename = medicinename;
		this.medicinedesc = medicinedesc;
		this.chategoryid = chategoryid;
		this.supplierid = supplierid;
		this.createdDate = createdDate;
	}

	@PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
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

	public Integer getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}	    
	
	// Getters and setters for all fields, including createdDate
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
	
	
	
	

}
