package com.dto;

import com.model.BuyMedicine;

public class BuyMedicineReportDTO {
    
    private Long id;
    private Integer medicineid;
    private Integer quantity;
    private Integer price;
    private String entryby;
    
    private java.sql.Date  makedate;
    private java.sql.Date  expairdate;
    private java.sql.Date entrydate;

    
    
    public BuyMedicineReportDTO(BuyMedicine buyMedicine) {
        // Convert LocalDate to java.sql.Date
        this.entrydate = java.sql.Date.valueOf(buyMedicine.getEntrydate());
        this.makedate=java.sql.Date.valueOf(buyMedicine.getMakedate());
        this.expairdate=java.sql.Date.valueOf(buyMedicine.getExpairdate());
        
        this.id = buyMedicine.getId();
        this.medicineid=buyMedicine.getMedicineid();
        this.quantity=buyMedicine.getQuantity();
        this.price=buyMedicine.getPrice();
        this.entryby=buyMedicine.getEntryby();

    }
    
    // Getters
    public java.sql.Date getEntrydate() {
        return entrydate;
    }

	public Long getId() {
		return id;
	}

	public Integer getMedicineid() {
		return medicineid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public java.sql.Date getMakedate() {
		return makedate;
	}

	public java.sql.Date getExpairdate() {
		return expairdate;
	}

	public String getEntryby() {
		return entryby;
	}

    
}