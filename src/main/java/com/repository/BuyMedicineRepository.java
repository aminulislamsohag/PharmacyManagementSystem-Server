package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.BuyMedicine;

@Repository
public interface BuyMedicineRepository extends JpaRepository<BuyMedicine, Long> {
	BuyMedicine findByid(Integer id);
	

   
}
