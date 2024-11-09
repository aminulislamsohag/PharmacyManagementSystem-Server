package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Medicine;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
		Medicine findByid(Integer id);
		
		List<Medicine> findByMedicineid(int medicineid);  // Search by exact id
		List<Medicine> findByMedicinenameContaining(String medicinename);  // Search by partial name
	   
}
