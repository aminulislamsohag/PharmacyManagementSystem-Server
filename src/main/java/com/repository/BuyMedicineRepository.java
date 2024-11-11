package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.BuyMedicine;

@Repository
public interface BuyMedicineRepository extends JpaRepository<BuyMedicine, Long> {
	BuyMedicine findByid(Integer id);
	
//here table name is model class name some 	
	@Query("Select  m.medicineid AS medicineid, m.medicinename AS medicinename,s.supplierid AS supplierid, s.suppliername AS suppliername, b.quantity AS quantity,"
		+ "	b.makedate AS makedate, b.expairdate AS expairdate,b.entryDate AS entryDate, b.entryby AS entryby "
		+ "from BuyMedicine b, Medicine m , Supplier s where  b.medicineid= m.medicineid and m.supplierid = s.supplierid")
	List<MedicineInfoProjection> fetchMedicineInfo();
	
   
}
