package com.coffee_bean.services;

import com.coffee_bean.exceptions.CategoryNotFoundException;
import com.coffee_bean.interfaces.CategoryService;
import com.coffee_bean.models.Category;
import com.coffee_bean.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new CategoryNotFoundException("Category not found!");
        }
    }
}
