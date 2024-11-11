package com.repository;


import java.time.LocalDate;

public interface MedicineInfoProjection {
	Integer getMedicineid();
    String getMedicinename();
    Integer getSupplierid();
    String getSuppliername();
    Integer getQuantity();
    LocalDate getMakedate();
    LocalDate getExpairdate();
    LocalDate getEntryDate();
    String getEntryby();

}
