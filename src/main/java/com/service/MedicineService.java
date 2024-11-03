package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Category;
import com.repository.CategoryRepository;



@Service
public class MedicineService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
