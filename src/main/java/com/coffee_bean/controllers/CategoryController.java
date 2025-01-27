package com.coffee_bean.controllers;

import com.coffee_bean.services.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImp categoryService;

    @GetMapping()
    public ResponseEntity<?> listAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
