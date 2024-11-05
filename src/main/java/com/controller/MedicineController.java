package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Category;
import com.model.Supplier;
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving chategory");
        }
    }	
	
    @PostMapping("/supplier")
    public ResponseEntity<?> addSupplier(@RequestBody Supplier supplier) {
    	Supplier  saveSupplier = medicineService.addSupplier(supplier);
        if (saveSupplier != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveSupplier);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving supplier");
        }
    }
    
    
    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getSuppliers() {
        List<Supplier> suppliers = medicineService.getAllSuppliers();
        if (!suppliers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(suppliers);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    
    @PutMapping("/editsupplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable("id") Long id, 
            @RequestBody Supplier updatedSupplierData) {
        Supplier updatedSupplier = medicineService.updateSupplier(id, updatedSupplierData);
        if (updatedSupplier != null) {
            return ResponseEntity.ok(updatedSupplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("deletesupplier/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        try {
        	medicineService.deleteSupplier(id);
            return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting supplier: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	
	
}
