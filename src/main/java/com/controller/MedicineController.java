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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Category;
import com.model.Medicine;
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
    
    @PostMapping("/addmedicine")
    public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine) {
    	Medicine  saveMedicine = medicineService.addMedicine(medicine);
        if (saveMedicine != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveMedicine);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving supplier");
        }
    }
    
    @GetMapping("/listmedicine")
    public ResponseEntity<List<Medicine>> getMedicine() {
        List<Medicine> listMedicine = medicineService.getAllMedicine();
        if (!listMedicine.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(listMedicine);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    
    @PutMapping("/editmedicine/{id}")
    public ResponseEntity<Medicine> updateMedicines( @PathVariable("id") Long id, @RequestBody Medicine updatedMedicineData) {
        Medicine updatedMedicine = medicineService.updateMedicine(id, updatedMedicineData);
        if (updatedMedicine != null) {
            return ResponseEntity.ok(updatedMedicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("deletemedicine/{id}")
    public ResponseEntity<String> deleteMedicinedata(@PathVariable Long id) {
        try {
        	medicineService.deleteMedicine(id);
            return new ResponseEntity<>("Medicine deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting Medicine: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      
    @GetMapping("/search")
    public List<Medicine> searchMedicine(@RequestParam("query") String query) {
        return medicineService.searchMedicine(query);
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
    
    //category 
    
    @GetMapping("/chategorys")
    public ResponseEntity<List<Category>> getCateorys() {
        List<Category> categroys = medicineService.getAllChategorys();
        if (!categroys.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(categroys);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    
    @PutMapping("/editchategory/{id}")
    public ResponseEntity<Category> updateCategory( @PathVariable("id") Long id,  @RequestBody Category updatedCategoryData) {
        Category updatedChategory = medicineService.updateCategory(id, updatedCategoryData);
        if (updatedChategory != null) {
            return ResponseEntity.ok(updatedChategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("deletechategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
        	medicineService.deletecategory(id);
            return new ResponseEntity<>("Chategory deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting Chategory: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	
	
}
