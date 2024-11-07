package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
		Medicine findByid(Integer id);
}
