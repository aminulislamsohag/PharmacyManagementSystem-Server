package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.BuyMedicine;
import com.service.BuyMedicineService;

@RestController
@RequestMapping("/buymedicine")
public class BuyMedicineController {

	@Autowired
    private BuyMedicineService buymedicineService;
	
    @PostMapping("/add")
    public ResponseEntity<?> addMedicine(@RequestBody BuyMedicine buymedicine) {
    	BuyMedicine  saveMedicine = buymedicineService.addBuyMedicine(buymedicine);
        if (saveMedicine != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveMedicine);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Buy Medicine");
        }
    }
	
	
	
	
}
