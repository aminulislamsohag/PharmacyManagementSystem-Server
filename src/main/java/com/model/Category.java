package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categorys")
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name="chategoryid")
    private Integer chategoryid;
    
    @Column(name="chategoryname", length = 255)
    private String chategoryname;
    
    @Column(name="chategorydesc", length = 255)
    private String chategorydesc;
    
    @Column(name="location", length = 255)
    private String location;
    
   
    @Column(name="supplierid", length = 255)
    private String supplierid;	    
    
    
    public Category() {}


	public Category(Long id, Integer chategoryid, String chategoryname, String chategorydesc, String location,
			String supplierid) {
		super();
		this.id = id;
		this.chategoryid = chategoryid;
		this.chategoryname = chategoryname;
		this.chategorydesc = chategorydesc;
		this.location = location;
		this.supplierid = supplierid;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getChategoryid() {
		return chategoryid;
	}


	public void setChategoryid(Integer chategoryid) {
		this.chategoryid = chategoryid;
	}


	public String getChategoryname() {
		return chategoryname;
	}


	public void setChategoryname(String chategoryname) {
		this.chategoryname = chategoryname;
	}


	public String getChategorydesc() {
		return chategorydesc;
	}


	public void setChategorydesc(String chategorydesc) {
		this.chategorydesc = chategorydesc;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getSupplierid() {
		return supplierid;
	}


	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
    
    

}
