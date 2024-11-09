package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.BuyMedicine;
import com.repository.BuyMedicineRepository;

@Service
public class BuyMedicineService {
	
	@Autowired
    private BuyMedicineRepository buyMedicineRepository;
	
    public BuyMedicine addBuyMedicine(BuyMedicine buymedicine) {
        return buyMedicineRepository.save(buymedicine);
    }

}
