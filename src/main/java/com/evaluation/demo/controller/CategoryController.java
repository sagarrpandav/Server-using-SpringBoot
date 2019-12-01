package com.evaluation.demo.controller;

import com.evaluation.demo.dto.CategoryDto;
import com.evaluation.demo.service.CategoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController
{
    @Autowired
    private CategoryServiceImplementation categoryServiceImplementation;

    @GetMapping(value = "/category")
    public List<CategoryDto> getAllCategories()
    {
        return categoryServiceImplementation.getAllCategories();
    };

}
