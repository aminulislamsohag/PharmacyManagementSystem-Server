package com.repository;


import java.time.LocalDate;

public interface MedicineInfoProjection {
	Integer getId();
	Integer getMedicineid();
    String getMedicinename();
    Integer getSupplierid();
    String getSuppliername();
    Integer getQuantity();
    Integer getPrice();
    LocalDate getMakedate();
    LocalDate getExpairdate();
    LocalDate getEntrydate();
    String getEntryby();
    Integer getVoucherid();

}
