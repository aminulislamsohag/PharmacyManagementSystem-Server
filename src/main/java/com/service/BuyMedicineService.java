package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.BuyMedicine;
import com.repository.BuyMedicineRepository;
import com.repository.MedicineInfoProjection;

@Service
public class BuyMedicineService {
	
	@Autowired
    private BuyMedicineRepository buyMedicineRepository;
	
    public BuyMedicine addBuyMedicine(BuyMedicine buymedicine) {
        return buyMedicineRepository.save(buymedicine);
    }
    public List<MedicineInfoProjection> getMedicineInfo() {
        return buyMedicineRepository.fetchMedicineInfo();
    }

}
