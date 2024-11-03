package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="suppliers")
public class Supplier {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private Long id;

	    @Column(name="supplierid")
	    private Integer supplierid;
	    
	    @Column(name="suppliername", length = 255)
	    private String suppliername;
	    
	    @Column(name="suppliercontract", length = 255)
	    private String suppliercontract;
	    
	    @Column(name="supplieraddress", length = 255)
	    private String supplieraddress;
	    
	    @Column(name="supplieremail", length = 255)
	    private String supplieremail;	    
	    
	    
	    public Supplier() {}


		public Supplier(Long id, Integer supplierid, String suppliername, String suppliercontract,
				String supplieraddress, String supplieremail) {
			super();
			this.id = id;
			this.supplierid = supplierid;
			this.suppliername = suppliername;
			this.suppliercontract = suppliercontract;
			this.supplieraddress = supplieraddress;
			this.supplieremail = supplieremail;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Integer getSupplierid() {
			return supplierid;
		}


		public void setSupplierid(Integer supplierid) {
			this.supplierid = supplierid;
		}


		public String getSuppliername() {
			return suppliername;
		}


		public void setSuppliername(String suppliername) {
			this.suppliername = suppliername;
		}


		public String getSuppliercontract() {
			return suppliercontract;
		}


		public void setSuppliercontract(String suppliercontract) {
			this.suppliercontract = suppliercontract;
		}


		public String getSupplieraddress() {
			return supplieraddress;
		}


		public void setSupplieraddress(String supplieraddress) {
			this.supplieraddress = supplieraddress;
		}


		public String getSupplieremail() {
			return supplieremail;
		}


		public void setSupplieremail(String supplieremail) {
			this.supplieremail = supplieremail;
		}
	    
		
		
	    
	
	
	
}
