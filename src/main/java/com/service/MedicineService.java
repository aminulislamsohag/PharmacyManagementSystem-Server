package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Category;
import com.model.Medicine;
import com.model.Supplier;
import com.repository.CategoryRepository;
import com.repository.MedicineRepository;
import com.repository.SupplierRepository;



@Service
public class MedicineService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private SupplierRepository supplierRepository;
	
	@Autowired
    private MedicineRepository medicineRepository;
	
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
    
    
    
    
    
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    public Supplier updateSupplier(Long id, Supplier updatedSupplierData) {
        Optional<Supplier> existingSupplierOpt = supplierRepository.findById(id);

        if (existingSupplierOpt.isPresent()) {
            Supplier existingSupplier = existingSupplierOpt.get();
            existingSupplier.setSuppliername(updatedSupplierData.getSuppliername());
            existingSupplier.setSuppliercontract(updatedSupplierData.getSuppliercontract());
            existingSupplier.setSupplieremail(updatedSupplierData.getSupplieremail());
            existingSupplier.setSupplieraddress(updatedSupplierData.getSupplieraddress());
            return supplierRepository.save(existingSupplier);
        } else {
            return null; // Supplier with the specified ID was not found
        }
    }
    
    public void deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("Supplier not found with ID: " + id);
        }
    }
    
    
    public List<Category> getAllChategorys() {
        return categoryRepository.findAll();
    }
    
    
    
    public Category updateCategory(Long id, Category updatedCategoryData) {
        Optional<Category> existingCategoryOpt = categoryRepository.findById(id);

        if (existingCategoryOpt.isPresent()) {
        	Category existingCategory = existingCategoryOpt.get();
        	existingCategory.setChategoryid(updatedCategoryData.getChategoryid());
        	existingCategory.setChategoryname(updatedCategoryData.getChategoryname());
        	existingCategory.setChategorydesc(updatedCategoryData.getChategorydesc());
        	existingCategory.setLocation(updatedCategoryData.getLocation());
        	existingCategory.setSupplierid(updatedCategoryData.getSupplierid());
            return categoryRepository.save(existingCategory);
        } else {
            return null; // Supplier with the specified ID was not found
        }
    }
    
    
    public void deletecategory(Long id) {
        if (categoryRepository.existsById(id)) {
        	categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Chategory not found with ID: " + id);
        }
    }
    
    
    
    
    
    
}
