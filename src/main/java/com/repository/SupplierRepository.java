package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Supplier;



@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Supplier findBysupplierid(Integer supplierid);
  
}