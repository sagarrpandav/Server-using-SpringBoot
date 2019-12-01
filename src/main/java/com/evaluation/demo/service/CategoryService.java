package com.evaluation.demo.service;

import com.evaluation.demo.dto.CategoryDto;
import com.evaluation.demo.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface CategoryService
{
    Category getCategoryByName(String categoryName);
    List<CategoryDto> getAllCategories();
}
