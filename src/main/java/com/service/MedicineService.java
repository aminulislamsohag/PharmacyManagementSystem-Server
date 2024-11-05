package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Category;
import com.model.Supplier;
import com.repository.CategoryRepository;
import com.repository.SupplierRepository;



@Service
public class MedicineService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private SupplierRepository supplierRepository;
	
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
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
    
    
    
    
}
