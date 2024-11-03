package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Category;
import com.service.MedicineService;


@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
    private MedicineService medicineService;
	 
    @PostMapping("/chategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
    	Category  saveCategory = medicineService.addCategory(category);
        if (saveCategory != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user");
        }
    }	
	
	
	
}
