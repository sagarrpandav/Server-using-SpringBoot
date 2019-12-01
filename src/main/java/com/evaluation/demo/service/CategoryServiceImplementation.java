package com.evaluation.demo.service;

import com.evaluation.demo.dto.CategoryDto;
import com.evaluation.demo.entity.Category;
import com.evaluation.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService
{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.getByCategoryName(categoryName);
    }

    @Override
    public List<CategoryDto> getAllCategories()
    {
        List<CategoryDto> categoryDtoList =new ArrayList<>();
        for(Category category : categoryRepository.findAll())
        {
            CategoryDto categoryDTO=new CategoryDto();
            categoryDTO.setCategoryID(category.getCategoryID());
            categoryDTO.setCategoryName(category.getCategoryName());

            categoryDtoList.add(categoryDTO);
        }

        return categoryDtoList;
    }
}