package com.dto;


import com.model.BuyMedicine;
import com.model.Medicine;
import com.model.Supplier;

public class VoucherReportDTO {


	    private Integer medicineid;
	    private String medicinename;
	    private Integer quantity;
	    private Integer price;
	    private String suppliername;
	    private String entryby;
	       
	    public VoucherReportDTO(BuyMedicine buyMedicine) {
	        this.medicineid = buyMedicine.getMedicineid();
	        this.medicinename = null; // or fetch the medicine name based on buyMedicine.getMedicineid()
	        this.quantity = buyMedicine.getQuantity();
	        this.price = buyMedicine.getPrice();
	        this.suppliername = null; // or fetch supplier name based on buyMedicine.getSupplierid()
	        this.entryby = buyMedicine.getEntryby();
	        
	        
	        
	    }

		public Integer getMedicineid() {
			return medicineid;
		}

		public String getMedicinename() {
			return medicinename;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public Integer getPrice() {
			return price;
		}

		public String getSuppliername() {
			return suppliername;
		}

		public String getEntryby() {
			return entryby;
		}
	    
	    
	    
	    
	    
	    
}
